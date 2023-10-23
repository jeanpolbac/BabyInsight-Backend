package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


/**
 * The AuthenticationTestDefs class contains step definitions for Cucumber scenarios
 * related to authentication and user registration.
 */
public class AuthenticationTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());
    private Response response;


    /**
     * Validates that the public endpoint is accessible.
     */
    //Scenario: User able to access public endpoints
    @Given("a valid public endpoint")
    public void aValidPublicEndpoint() {
        logger.info("Scenario: User able to access public endpoints - Step: A valid public endpoint");
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(BASE_URL + port + helloEndpoint, HttpMethod.GET, null, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * Sends a request to the public endpoint.
     */
    @When("I say hello")
    public void iSayHello() {
        logger.info("Scenario: User able to access public endpoints - Step: I say hello");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + helloEndpoint);
    }

    /**
     * Validates that the expected message is returned from the public endpoint.
     */
    @Then("Hello is shown")
    public void helloIsShown() {
        logger.info("Scenario: User able to access public endpoints - Step: Hello is shown");
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Hello World", message);
    }


    /**
     * Navigates to the registration page.
     * @param register the registration endpoint
     */
    // Scenario: Successful registration
    @Given("I am on the registration page {string}")
    public void iAmOnTheRegistrationPage(String register) {
        logger.info("Scenario: Successful registration - Step: I am on the registration page");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + port + register)
                .then()
                .extract()
                .response();
        int statusCode = response.statusCode();
        assert statusCode == 200 : "Expected status code to be 200, but it was: " + statusCode;
    }

    /**
     * Submits the registration form with a valid email address and strong password.
     * @param emailAddress the email address
     * @param password the password
     */
    @When("I enter a valid email address {string} and a strong password {string}")
    public void iEnterAValidEmailAddressAndAStrongPassword(String emailAddress, String password) {
        logger.info("Scenario: Successful registration - Step: I enter a valid email address and a strong password");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("emailAddress", emailAddress);
            requestBody.put("password", password);
            request.header("Content-Type", "application/json");
            response = request.body(requestBody.toString()).post(BASE_URL + port + registerEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Failed Exception");
        }
    }

    /**
     * Validates that the registration was successful.
     */
    @Then("I should be successfully registered")
    public void iShouldBeSuccessfullyRegistered() {
        logger.info("Scenario: Successful registration - Step: I should be successfully registered");
        Assert.assertEquals(201,response.getStatusCode());
    }


    /**
     * Authenticates the user.
     */
    // Scenario: User able to login and receive jwt token
    @Given("I am an authenticated user")
    public void iAmAnAuthenticatedUser() throws JSONException {
        logger.info("Scenario: User able to login and receive jwt token - Step: I am an authenticated user");
        RequestSpecification request = RestAssured.given();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emailAddress", "coolparent@email.com");
        jsonObject.put("password","password123");
        response = request.contentType(ContentType.JSON).body(jsonObject.toString()).post(BASE_URL + port + loginEndpoint);
    }

    /**
     * Validates the user's details.
     */
    @When("the user details are validated")
    public void theUserDetailsAreValidated() {
        logger.info("Scenario: User able to login and receive jwt token - Step: The user details are validated");
        Assert.assertEquals(200, response.getStatusCode());
    }

    /**
     * Validates that a JWT token is received upon successful authentication.
     */
    @Then("the user receives a jwt token")
    public void theUserReceivesAJwtToken() {
        logger.info("Scenario: User able to login and receive jwt token - Step: The user receives a jwt token");
        Assert.assertNotNull(response.jsonPath().getString("jwt"));
    }
}
