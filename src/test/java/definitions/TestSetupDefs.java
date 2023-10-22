package definitions;

import com.example.babyinsightbackend.BabyInsightBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;


/**
 * The TestSetupDefs class is a configuration class for setting up Cucumber tests.
 * It also includes utility methods for authentication and common setup.
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BabyInsightBackendApplication.class)
public class TestSetupDefs {

    private static final Logger logger = Logger.getLogger(TestSetupDefs.class.getName());

    // Base URL for testing
    public static final String BASE_URL = "http://localhost:";


    // Authentication Endpoints
    public static final String helloEndpoint = "/hello/";
    public static final String registerEndpoint = "/auth/users/register/";
    public static final String loginEndpoint = "/auth/users/login/";


    // Child Management Endpoints
    public static final String childrenEndpoint = "/api/parents/{parentID}/children/";
    public static final String childEndpoint = "/api/parents/{parentID}/children/{childID}/";


    // Vaccines Management Endpoints
    public static final String allVaccinesEndpoint = "/api/vaccines/";
    public static final String childVaccinesEndpoint = "/api/parents/{parentID}/children/{childID}/vaccines/";
    public static final String remainingVaccinesEndpoint = "/api/parents/{parentID}/children/{childID}/vaccines/remaining/";
    public static final String specificChildVaccineEndpoint = "/api/parents/{parentID}/children/{childID}/vaccines/{vaccineID}/";
    public static final String overdueVaccinesEndpoint = "/api/parents/{parentID}/children/{childID}/vaccines/overdue/";


    // Interaction Checker Endpoints
    public static final String interactionEndpoint = "/api/interactions?med1={medicationID1}&med2={medicationID2}";


    // Medication Log Endpoints
    public static final String childMedicationsEndpoint = "/api/children/{childID}/medications/";
    public static final String addMedicationEndpoint = "/api/medications/add/";
    public static final String specificMedicationEndpoint = "/api/medications/{medicationID}/";
    public static final String archivedMedicationsEndpoint = "/api/medications/archived/";

    // Content-Type Json
    public static final String TypeJson = "application/json";


    @LocalServerPort
    public String port;



}
