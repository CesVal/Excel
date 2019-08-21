//package pe.com.access.control.AccesControl.rest;
//
//import com.opencsv.CSVWriter;
//import com.opencsv.bean.ColumnPositionMappingStrategy;
//import com.opencsv.bean.StatefulBeanToCsv;
//import com.opencsv.bean.StatefulBeanToCsvBuilder;
//import com.opencsv.exceptions.CsvException;
//import lombok.extern.slf4j.Slf4j;
//import pe.com.access.control.AccesControl.dto.UserResponse;
//
//import java.io.PrintWriter;
//import java.util.List;
//
//@Slf4j
//public class WriteCsvToResponse {
//
//    public static void writeUsers(PrintWriter writer, List<UserResponse> userResponses) {
//
//        try {
//
//            ColumnPositionMappingStrategy mapStrategy
//                    = new ColumnPositionMappingStrategy();
//
//            mapStrategy.setType(UserResponse.class);
//            mapStrategy.generateHeader();
//
//            String[] columns = new String[]{"userId", "firstName", "lastName"};
//            mapStrategy.setColumnMapping(columns);
//
//            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder<>(writer)
//                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                    .withMappingStrategy(mapStrategy)
//                    .withSeparator('|')
//                    .build();
//
//            btcsv.write(userResponses);
//
//        } catch (CsvException ex) {
//            log.error("Error mapping Bean to CSV", ex);
//        }
//    }
//
//}
