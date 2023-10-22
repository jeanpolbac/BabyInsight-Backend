package definitions;

import com.example.babyinsightbackend.BabyInsightBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;

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
    public static final String helloEndpoint = "/auth/parents/hello/";
    public static final String registerEndpoint = "/auth/parents/register/";
    public static final String loginEndpoint = "/auth/parents/login/";


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


    /**
     * Creates headers for authenticated requests.
     * @return a HttpHeaders object with an Authorization header containing a JWT token and a Content-Type header set to application/json
     * @throws JSONException if there's an error while creating the JSON request body
     */
    protected HttpHeaders createAuthHeaders() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getJWTToken());
        headers.add("Content-Type", TypeJson);
        return headers;
    }

    /**
     * Retrieves a JWT token for authentication.
     * @return a JWT token as a String
     * @throws JSONException if there's an error while creating the JSON request body
     */
    protected String getJWTToken() throws JSONException {
        // Set the base URI and create a request
        RestAssured.baseURI = BASE_URL + port;
        RequestSpecification request = RestAssured.given();

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", TypeJson);

        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "test@email.com");
        requestBody.put("password", "password12345");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + loginEndpoint);

        // Debugging: Log the response content and HTTP status
        String responseBody = response.getBody().asString();
        logger.info("Response Body: " + responseBody);
        int statusCode = response.getStatusCode();
        logger.info("HTTP Status Code: " + statusCode);

        // Extract and return the JWT token from the authentication response
        // Check for the presence of JWT and extract
        if (response.jsonPath().get("jwt") != null) {
            return response.jsonPath().getString("jwt");
        } else {
            throw new RuntimeException("JWT not found in the response");
        }
    }
}
