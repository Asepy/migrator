package py.com.asepy.migrator.core;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import py.com.asepy.migrator.entity.MembersEntity;
import py.com.asepy.migrator.mapper.MemberMapper;
import py.com.asepy.migrator.repository.MemberRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component
public class Migrator {

    Logger logger = LoggerFactory.getLogger(Migrator.class);

    private static final String CSV_FILE_NAME = "input.csv";

    private final MemberRepository repository;
    private final MemberMapper memberMapper;

    public Migrator(MemberRepository repository,
                    MemberMapper memberMapper){
        this.repository = repository;
        this.memberMapper = memberMapper;
    }

    public void startMigration() throws IOException, CsvException{
        logger.info("Starting migration");
        List<String[]> allRows = getAllRowsFromCsvFileWithName(CSV_FILE_NAME);
        String[] csvHeaders = allRows.get(2);
        logger.info("CSV Head {}", Arrays.toString(csvHeaders));
        Map<Integer, String> csvHeaderPositionAttNameMap = buildCsvPositionAttMap(csvHeaders);
        logger.info("CSV Headers mapping {}", csvHeaderPositionAttNameMap);
        List<String> migrationErrors = new ArrayList<>();
        mapRowsToMemberAndSaveOrLoadErrors(allRows, csvHeaderPositionAttNameMap, migrationErrors);
        writeErrorsFile(migrationErrors);

    }

    private List<String[]> getAllRowsFromCsvFileWithName(String csvFileName) throws IOException, CsvException{
        try (FileReader filereader = new FileReader(csvFileName)) {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();
            return  csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Map<Integer, String> buildCsvPositionAttMap(String[] csvHeaders) {
        Map<Integer, String> positionDBColumnNameMap = new HashMap<>();
        for (int i = 0 ; i < csvHeaders.length; i++) {
            positionDBColumnNameMap.put(i, csvHeaders[i]);
        }
        return positionDBColumnNameMap;
    }

    private void mapRowsToMemberAndSaveOrLoadErrors(List<String[]> allRows, Map<Integer, String> csvHeaderPositionAttNameMap, List<String> errors) {
        for (int i = 2; i < allRows.size(); i++) {
            String[] row = allRows.get(i);
            if (row[2].equals("Status")) continue;
            logger.info("Row to process: {} ", Arrays.toString(row));
            MembersEntity memberEntity = memberMapper.fromCsvRow(csvHeaderPositionAttNameMap, row);
            Optional<MembersEntity> currentMember = repository.findFirstByRefId(memberEntity.getRefId());
            if (currentMember.isPresent()) {
                memberEntity.setId(currentMember.get().getId());
                memberEntity.setStartDate(currentMember.get().getStartDate());
            } else {
                memberEntity.setId(UUID.randomUUID().toString());
                memberEntity.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
            }
            logger.info("Entity to save : {}", memberEntity);
            String rowNumber = row[0];
            saveMemberRowOrAddErrorWithPrefix(memberEntity, errors, rowNumber);
        }
    }

    private void saveMemberRowOrAddErrorWithPrefix(MembersEntity memberEntity, List<String> errors, String errorPrefix) {
        try {
            repository.save(memberEntity);
        }catch (DataAccessException e) {
            logger.warn("Error in this row");
            String errorMessage = errorPrefix + " - Error: "+e.getCause().getCause().getMessage();
            errors.add(errorMessage);
        }
    }

    private void writeErrorsFile(List<String> rowsWithProblems) {
        if(!rowsWithProblems.isEmpty()){
            String fileName = buildErrorFileName();
            logger.info("Writing error file {}", fileName);
            createErrorFIle(rowsWithProblems, fileName);
        }else {
            logger.info("No errors detected");
        }
    }

    private String buildErrorFileName(){
        LocalDateTime now = LocalDateTime.now();
        String nowFormatted = now.format(DateTimeFormatter.ISO_DATE_TIME);
        return "migrations-errors-"+nowFormatted+".txt";
    }

    private void createErrorFIle(List<String> rowsWithProblems, String fileName) {
        try ( FileOutputStream fos = new FileOutputStream(fileName);
              DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos))) {
            for(String error : rowsWithProblems){
                Files.writeString(Path.of(fileName), error + System.lineSeparator(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            logger.error("Error trying to create errors file", e);
            logger.warn("Error showing on screen ");
            rowsWithProblems.forEach(logger::warn);
        }
    }
}
