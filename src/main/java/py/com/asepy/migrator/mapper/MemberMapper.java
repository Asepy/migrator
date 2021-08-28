package py.com.asepy.migrator.mapper;

import org.springframework.stereotype.Component;
import py.com.asepy.migrator.entity.MembersEntity;
import py.com.asepy.migrator.repository.CityRepository;
import py.com.asepy.migrator.repository.DepartmentRepository;
import py.com.asepy.migrator.repository.RubroRepository;

import java.util.Map;

@Component
public class MemberMapper {

    private final DepartmentRepository departmentRepository;
    private final CityRepository cityRepository;
    private final RubroRepository rubroRepository;

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
            //TODO tambien faltaria el membership type
            switch (csvHeaderPositionNameMap.get(i)) {
                case "ID":
                    member.setRefId(Integer.parseInt(cell));
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
                //TODO estee caso verificar mejor
                case "Cantidad de empleados (si no aplica: 0)":
                    if(!cell.isBlank() || !cell.isEmpty()){
                        member.setNumberEmployees(Integer.parseInt(cell.trim()));
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
                    //TODO aqui hay que ver los formatos posibles
                    //identifico dd/MM/yy, yyyy-MM-dd
                    String birthDate = cell;
                    //member.set(cell);
                    break;
                case "Celular":
                    if(cell.isEmpty() || cell.isBlank()) break;
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

                    member.setCellphone(cellphoneToInsert);
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
}
