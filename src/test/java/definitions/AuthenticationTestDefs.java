package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public class AuthenticationTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());

    private static Response response;



    //Scenario: User able to access public endpoints
    @Given("a valid public endpoint")
    public void aValidPublicEndpoint() {
        logger.info("Scenario: User able to access public endpoints - Step: A valid public endpoint");
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(BASE_URL + port + helloEndpoint, HttpMethod.GET, null, String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @When("I say hello")
    public void iSayHello() {
        logger.info("Scenario: User able to access public endpoints - Step: I say hello");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + helloEndpoint);
    }

    @Then("Hello is shown")
    public void helloIsShown() {
        logger.info("Scenario: User able to access public endpoints - Step: Hello is shown");
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Hello", message);
    }

    // Scenario: Successful registration
    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {

    }


    @When("I enter a valid email address and a strong password")
    public void iEnterAValidEmailAddressAndAStrongPassword() {

    }

    @Then("I should be successfully registered")
    public void iShouldBeSuccessfullyRegistered() {

    }

    // Scenario: User able to login and receive jwt token
    @Given("the registered user exists")
    public void theRegisteredUserExists() {

    }

    @When("the user details are validated")
    public void theUserDetailsAreValidated() {

    }

    @Then("the user receives a jwt token")
    public void theUserReceivesAJwtToken() {
    }
}
