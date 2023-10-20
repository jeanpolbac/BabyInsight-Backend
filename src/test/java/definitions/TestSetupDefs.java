package definitions;

import com.example.babyinsightbackend.BabyInsightBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BabyInsightBackendApplication.class)
public class TestSetupDefs {

    // Base URL for testing
    public static final String BASE_URL = "http://localhost:";


    // Authentication Endpoints
    public static final String helloEndpoint = "/auth/parents/hello/";
    public static final String registerEndpoint = "/auth/parents/register/";
    public static final String loginEndpoint= "/auth/parents/login/";


    // Child Management Endpoints
    public static final String childrenEndpoint = "/api/parents/{parentID}/children";
    public static final String childEndpoint= "/api/parents/{parent_id}/children/{child_id}";


    // Vaccines Management Endpoints
    public static final String allVaccinesEndpoint = "/api/vaccines/";


    // Interaction Checker Endpoints
    public static final String interactionEndpoint= "/api/interactions?med1={medicationID1}&med2={medicationID2}/";


    // Medication Log Endpoints
    public static final String allMedicationEndpoint= "/api/medications/{childID}/";


    // Content-Type Json
    public static final String TypeJson = "application/json";


    @LocalServerPort
    public String port;


}
