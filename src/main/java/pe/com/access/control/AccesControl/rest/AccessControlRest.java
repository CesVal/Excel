package pe.com.access.control.AccesControl.rest;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pe.com.access.control.AccesControl.dto.UserResponse;
import pe.com.access.control.AccesControl.service.AccessControlService;
import pe.com.access.control.AccesControl.service.Person;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccessControlRest {

    @Autowired
    private AccessControlService accessControlService;

    @GetMapping("/access-control")
    public UserResponse getAccessControlUser() {
        return accessControlService.accessControlUser();
    }

//    @PostMapping(value = "/export", produces = "text/csv")
//    public void findCities(HttpServletResponse response) throws IOException {
//
//
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + "Example.csv" + "\"");
//
//        List<UserResponse> responseList = new ArrayList<>();
//
//        UserResponse userResponse1 = new UserResponse();
//        userResponse1.setUserId("1");
//        userResponse1.setFirstName("Cesar");
//        userResponse1.setLastName("Valverde");
//
//        UserResponse userResponse2 = new UserResponse();
//        userResponse2.setUserId("2");
//        userResponse2.setFirstName("Johan");
//        userResponse2.setLastName("Villanueva");
//
//        responseList.add(userResponse1);
//        responseList.add(userResponse2);
//
//        WriteCsvToResponse.writeUsers(response.getWriter(), responseList);
//    }

    //    @GetMapping(value = "/export")
//    @RequestMapping(value = "/export", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping(value = "/export")
    public void findCities(HttpServletResponse response) throws Exception {

        List<Person> responseList = new ArrayList<>();

        Person userResponse1 = new Person();
        userResponse1.setId("1");
        userResponse1.setName("Cesar");
        userResponse1.setLastname("Valverde");

        Person userResponse2 = new Person();
        userResponse2.setId("2");
        userResponse2.setName("Johan");
        userResponse2.setLastname("Villanueva");

        responseList.add(userResponse1);
        responseList.add(userResponse2);

        //set file name and content type
        String filename = "orders_report.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");


        final CustomMappingStrategy<Person> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(Person.class);


        //create a csv writer
        StatefulBeanToCsv<Person> writer = new StatefulBeanToCsvBuilder<Person>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(mappingStrategy)
                .withSeparator('|')
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(responseList);
    }
}

class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
    private static final String[] HEADER = new String[]{"userId", "firstName", "lastName"};

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        return HEADER;
    }

}