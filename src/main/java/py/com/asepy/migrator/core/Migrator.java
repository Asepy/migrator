package py.com.asepy.migrator.core;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import py.com.asepy.migrator.entity.MembersEntity;
import py.com.asepy.migrator.mapper.MemberMapper;
import py.com.asepy.migrator.repository.MemberRepository;

import java.io.FileReader;
import java.util.*;


@Component
public class Migrator {

    Logger logger = LoggerFactory.getLogger(Migrator.class);

    private final MemberRepository repository;
    private final MemberMapper memberMapper;

    public Migrator(MemberRepository repository,
                    MemberMapper memberMapper){
        this.repository = repository;
        this.memberMapper = memberMapper;
    }

    public void startMigration(){
        logger.info("Starting migration");
        readDataFromCustomSeparator("input.csv");
    }

    public void readDataFromCustomSeparator(String file) {
        // Create an object of file reader class with CSV file as a parameter.
        try (FileReader filereader = new FileReader(file)){
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = csvReader.readAll();

            String[] csvHeaders = allData.get(0);
            logger.info("CSV Head");
            logger.info(Arrays.toString(csvHeaders));
            Map<Integer, String> csvHeaderPositionAttNameMap = getCsvPositionAttMap(csvHeaders);
            logger.info("CSV Headers mapping");
            logger.info(String.valueOf(csvHeaderPositionAttNameMap));
            // Print Data.
            for (int i= 1; i < allData.size() ; i++) {
                String[] row = allData.get(i);
                logger.info("Row to process:");
                logger.info(Arrays.toString(row));
                MembersEntity memberEntity = memberMapper.fromCsvRow(csvHeaderPositionAttNameMap, row);
                Optional<MembersEntity> currentMember = repository.findFirstByRefId(memberEntity.getRefId());
                if(currentMember.isPresent()){
                    memberEntity.setId(currentMember.get().getId());
                } else{
                    memberEntity.setId(UUID.randomUUID().toString());
                }
                logger.info("Entity to save:");
                logger.info(memberEntity.toString());
                repository.save(memberEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> getCsvPositionAttMap(String[] csvHeaders) {
        Map<Integer, String> positionDBColumnNameMap = new HashMap<>();
        for (int i = 0 ; i < csvHeaders.length; i++) {
            positionDBColumnNameMap.put(i, csvHeaders[i]);
        }
        return positionDBColumnNameMap;
    }
}
