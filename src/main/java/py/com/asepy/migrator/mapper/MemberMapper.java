package py.com.asepy.migrator.mapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import py.com.asepy.migrator.entity.MembersEntity;
import py.com.asepy.migrator.repository.CityRepository;
import py.com.asepy.migrator.repository.DepartmentRepository;
import py.com.asepy.migrator.repository.RubroRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class MemberMapper {

    Logger logger = LoggerFactory.getLogger(MemberMapper.class);


    private final DepartmentRepository departmentRepository;
    private final CityRepository cityRepository;
    private final RubroRepository rubroRepository;
    private final SimpleDateFormat FORMAT_DDMMYY = new SimpleDateFormat("dd/MM/yy");
    private final SimpleDateFormat FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    MemberMapper( DepartmentRepository departmentRepository, CityRepository cityRepository, RubroRepository rubroRepository){
        this.departmentRepository = departmentRepository;
        this.cityRepository = cityRepository;
        this.rubroRepository = rubroRepository;
    }

    public MembersEntity fromCsvRow(Map<Integer, String> csvHeaderPositionNameMap, String[] csvRow, List<String> warnings){
        MembersEntity member = new MembersEntity();
        for (int i = 0; i< csvRow.length ; i++){
            String cell = csvRow[i];
            if(cell == null || "-".equals(cell)) continue;
            switch (csvHeaderPositionNameMap.get(i)) {
                case "ID":
                    member.setRefId(Integer.parseInt(cell));
                    break;
                case "Membresía":
                    member.setMembershipTypeFromCsv(cell);
                    break;
                case "Nombre":
                    member.setName(cell);
                    break;
                case "Apellido":
                    member.setSurname(cell);
                    break;
                case "Mail":
                    member.setMailId(cell);
                    break;
                case "Cédula de Identidad":
                    member.setNationalId(cell.replace(".", ""));
                    break;
                case "R.U.C":
                    if(cell.length()<13 && cell.matches(".*\\d.*")){
                        member.setRuc(cell);
                    }else{
                      logger.warn("RUC no processed");
                      warnings.add(csvRow[0]+ " - RUC ["+cell+"] with invalid format, no processed");
                    }
                    break;
                case "Razón social":
                    member.setBusinessName(cell);
                    break;
                case "Nombre de fantasía":
                    member.setFancyBusinessName(cell);
                    break;
                case "Sexo":
                    member.setGender(cell);
                    break;
                case "Rubro":
                    rubroRepository.getByDescription(cell)
                            .ifPresent(rubro -> member.setRubroId(rubro.getId()));
                    break;
                case "Año de fundación":
                    Year foundationYear = parseFoundationYear(csvRow, warnings, cell);
                    member.setStartedBusinessYear(foundationYear);
                    break;
                case "Cantidad de empleados (si no aplica: 0)":
                    Integer emplyeeQuantity = parseEmployeeQuantity(csvRow, warnings, cell);
                    member.setNumberEmployees(emplyeeQuantity);
                    break;
                case "Sitio web o redes sociales":
                    member.setWebsite(cell);
                    break;
                case "Nivel de Estudios":
                    if(cell.length()<31){
                        member.setEducationLevel(cell);
                    }else {
                        logger.warn("Education Level too large");
                        logger.info("Adding to warnings");
                        warnings.add(csvRow[0]+ " - EDUCATION LEVEL ["+ cell +"] too large, no processed");
                    }

                    break;
                case "Departamento":
                    departmentRepository.getByName(cell)
                            .ifPresent(d -> member.setDepartmentId(d.getId()));
                    break;
                case "Ciudad":
                    cityRepository.getByName(cell)
                            .ifPresent(city -> member.setCityId(city.getId()));
                    break;
                case "Fecha de nacimiento":
                    if (StringUtils.isNotBlank(cell)) {
                        member.setBirthdate(parseBirthDate(cell));
                    }
                    break;
                case "Celular":
                    if(cell.isEmpty() || StringUtils.isBlank(cell)) {
                        break;
                    }
                    member.setCellphone(parseCellphone(cell, csvRow, warnings));
                    break;
                case "LinkedIn":
                    if (cell.length()>150){
                        cell = cell.substring(0,100);
                    }
                    member.setLinkedinProfile(cell);
                    break;
                case "Twitter":
                    if (cell.length()>150){
                        cell = cell.substring(0,100);
                    }
                    member.setTwitterProfile(cell);
                    break;
                case "Facebook":
                    if (cell.length()>150){
                        cell = cell.substring(0,100);
                    }
                    member.setFacebookProfile(cell);
                    break;
                default:
                    break;
            }
        }

        //Checking default nun null values
        if(member.getEmailStatus() == null) {
            member.setEmailStatus("VALID");
        }
        if(member.getStatus() == null) {
            member.setStatus("ACTIVE");
        }
        return member;
    }

    private Year parseFoundationYear(String[] csvRow, List<String> warnings, String cell) {
        if(StringUtils.isNotBlank(cell)){
            try{
                return Year.parse(cell, DateTimeFormatter.ofPattern("yyyy"));
            }catch (Exception e){
                logger.warn("Error trying to parse the foundation year", e);
                logger.info("Adding to warnings");
                warnings.add(csvRow[0]+ " - FOUNDATION YEAR - ["+ cell +"] with invalid format, must be like 2020, no processed");
                return null;
            }
        }
        return null;
    }

    private Integer parseEmployeeQuantity(String[] csvRow, List<String> warnings, String cell) {
        if(StringUtils.isNotBlank(cell) || !cell.isEmpty()){
            try {
                return Integer.parseInt(cell.trim());
            }catch (NumberFormatException e){
                logger.warn("Error trying to parse employees quantity", e);
                logger.info("Adding to warnings");
                warnings.add(csvRow[0]+ " - EMPLOYEES QUANTITY - ["+ cell +"] must be a number, no processed");
                return null;
            }
        }
        return null;
    }

    private String parseCellphone(String cell, String[] rows, List<String> warnings) {
        String cellphoneInRow = cell;
        String cellphoneToInsert;

        //TODO este caso verificarlo bien
        if(cellphoneInRow.contains("/")){
            cellphoneInRow = cellphoneInRow.split("/")[0];
        }
        if(cellphoneInRow.startsWith("595")){
            cellphoneToInsert = cellphoneInRow.replace("595", "0");
        }else if(!cellphoneInRow.startsWith("0")){
            cellphoneToInsert = "0" + cellphoneInRow;
        }else {
            cellphoneToInsert = cellphoneInRow.trim();
        }

        cellphoneToInsert = cellphoneToInsert.trim().replace(" ", "");

        if(cellphoneToInsert.length()>10) {
            logger.warn("Cellphone will be shortened");
            logger.info("Adding to warnings");
            cellphoneToInsert = cellphoneToInsert.substring(0,9);
            warnings.add(rows[0]+" - CELLPHONE is shortened to ["+cellphoneToInsert+"]");
            return cellphoneToInsert;
        } else return cellphoneToInsert;
    }

    private Date parseBirthDate(String cell) {
        java.util.Date parse = null;
        try {
            parse = FORMAT_DDMMYY.parse(cell);
        } catch (ParseException e) {
        }

        try {
            parse = FORMAT_YYYYMMDD.parse(cell);
        } catch (ParseException e) {
        }

        if (parse != null) {
            return new java.sql.Date(parse.getTime());
        }

        return null;
    }
}
