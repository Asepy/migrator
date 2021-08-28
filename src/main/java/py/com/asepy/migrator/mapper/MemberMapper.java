package py.com.asepy.migrator.mapper;

import org.apache.commons.lang3.StringUtils;
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
import java.util.Map;

@Component
public class MemberMapper {

    private final DepartmentRepository departmentRepository;
    private final CityRepository cityRepository;
    private final RubroRepository rubroRepository;
    private SimpleDateFormat FORMAT_DDMMYY = new SimpleDateFormat("dd/MM/yy");
    private SimpleDateFormat FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    MemberMapper( DepartmentRepository departmentRepository, CityRepository cityRepository, RubroRepository rubroRepository){
        this.departmentRepository = departmentRepository;
        this.cityRepository = cityRepository;
        this.rubroRepository = rubroRepository;
    }

    public MembersEntity fromCsvRow(Map<Integer, String> csvHeaderPositionNameMap  , String[] csvRow){
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
                    member.setNationalId(cell);
                    break;
                case "R.U.C":
                    member.setRuc(cell);
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
                    member.setStartedBusinessYear(Year.parse(cell, DateTimeFormatter.ofPattern("yyyy")));
                    break;
                case "Cantidad de empleados (si no aplica: 0)":
                    if(StringUtils.isNotBlank(cell) || !cell.isEmpty()){
                        member.setNumberEmployees(Integer.parseInt(cell.trim()));
                    }
                    break;
                case "Sitio web o redes sociales":
                    member.setWebsite(cell);
                    break;
                case "Nivel de Estudios":
                    member.setEducationLevel(cell);
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
                    member.setCellphone(parseCellphone(cell));
                    break;
                case "LinkedIn":
                    member.setLinkedinProfile(cell);
                    break;
                case "Twitter":
                    member.setTwitterProfile(cell);
                    break;
                case "Facebook":
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

    private String parseCellphone(String cell) {
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
            cellphoneToInsert = cellphoneInRow;
        }

        return cellphoneToInsert;
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
