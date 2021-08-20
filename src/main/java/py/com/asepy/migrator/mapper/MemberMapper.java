package py.com.asepy.migrator.mapper;

import org.springframework.stereotype.Component;
import py.com.asepy.migrator.entity.MembersEntity;

import java.util.Map;

@Component
public class MemberMapper {

    public MembersEntity fromCsvRow(Map<Integer, String> csvHeaderPositionNameMap  , String[] csvRow){
        MembersEntity member = new MembersEntity();
        for (int i = 0; i< csvRow.length ; i++){
            String cell = csvRow[i];
            switch (csvHeaderPositionNameMap.get(i)) {
                case "id":
                    member.setRefId(Integer.parseInt(cell));
                    break;
                case "name":
                    member.setName(cell);
                    break;
                case "surname":
                    member.setSurname(cell);
                    break;
                case "website":
                    member.setWebsite(cell);
                    break;
                case "national_id":
                    member.setNationalId(cell);
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
