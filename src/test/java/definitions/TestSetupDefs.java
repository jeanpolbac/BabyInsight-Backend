package definitions;

import com.example.babyinsightbackend.BabyInsightBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

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
    public static final String childrenEndpoint = "/api/users/{userId}/children/";
    public static final String childEndpoint = "/api/users/{userId}/children/{childId}/";


    // Vaccines Management Endpoints
    public static final String allVaccinesEndpoint = "/api/vaccines/";
    public static final String childVaccinesEndpoint = "/api/users/{userId}/children/{childId}/vaccines/";
    public static final String remainingVaccinesEndpoint = "/api/users/{userId}/children/{childId}/vaccines/remaining/";
    public static final String specificChildVaccineEndpoint = "/api/users/{userId}/children/{childId}/vaccines/{vaccineID}/";
    public static final String overdueVaccinesEndpoint = "/api/users/{userId}/children/{childId}/vaccines/overdue/";


    // Interaction Checker Endpoints
    public static final String interactionEndpoint = "/api/interactions?med1={medicationId1}&med2={medicationId2}";


    // Medication Log Endpoints
    public static final String childMedicationsEndpoint = "/api/children/{childId}/medications/";
    public static final String addMedicationEndpoint = "/api/medications/add/";
    public static final String specificMedicationEndpoint = "/api/medications/{medicationId}/";
    public static final String archivedMedicationsEndpoint = "/api/medications/archived/";

    // Content-Type Json
    public static final String TypeJson = "application/json";


    @LocalServerPort
    public String port;

    protected static String token;
    /**
     * Creates a HttpHeaders object with the Authorization and Content-Type headers.
     *
     * @return the HttpHeaders object
     */
    protected HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", TypeJson);
        return headers;
    }



}
