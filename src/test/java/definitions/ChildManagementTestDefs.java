package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.logging.Logger;

public class ChildManagementTestDefs extends TestSetupDefs{

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());

    private static Response response;

    // Scenario: Parent able to add a child to their profile for tracking vaccines and medication
    @Given("the parent is logged in with credentials {string} and password {string}")
    public void theParentIsLoggedInWithCredentialsAndPassword(String emailAddress, String password) {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The parent is logged in");
        try {
            RequestSpecification request = RestAssured.given();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("emailAddress", emailAddress);
            jsonObject.put("password", password);

            response = request.contentType(ContentType.JSON).body(jsonObject.toString()).post(BASE_URL + port + loginEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    @When("the parent adds a child with name {string} and date of birth {string}")
    public void theParentAddsAChildWithNameAndDateOfBirth(String childName, String dob) {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The parent adds a child");
        try {
            RequestSpecification request = RestAssured.given();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("childName", childName);
            jsonObject.put("dateOfBirth", dob);

            response = request.contentType(ContentType.JSON).body(jsonObject.toString()).post(BASE_URL + port + childEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    @Then("the child should be successfully added to the parent's profile")
    public void theChildShouldBeSuccessfullyAddedToTheParentSProfile() {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The child is successfully added");
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getString("childId"));
    }

    @When("the parent views the list of their children")
    public void theParentViewsTheListOfTheirChildren() {
        
    }

    @Then("the list should include {string}")
    public void theListShouldInclude(String childName) {
        
    }

    @And("the child {string} exists in the parent's profile")
    public void theChildExistsInTheParentSProfile(String childName) {
        
    }

    @When("the parent edits the child's name to {string}")
    public void theParentEditsTheChildSNameTo(String childName) {
        
    }

    @Then("the child's name should be updated to {string}")
    public void theChildSNameShouldBeUpdatedTo(String childName) {
        
    }

    @When("the parent views the profile of {string}")
    public void theParentViewsTheProfileOf(String childName) {
        
    }

    @Then("the profile should display the name {string} and date of birth {string}")
    public void theProfileShouldDisplayTheNameAndDateOfBirth(String childName, String dob) {
    }
}