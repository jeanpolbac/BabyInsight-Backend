package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;


import java.util.List;
import java.util.logging.Logger;

/**
 * The ChildManagementTestDefs class contains step definitions for Cucumber scenarios
 * related to managing child profiles by a parent.
 */
public class ChildManagementTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(ChildManagementTestDefs.class.getName());

    private static Response response;


    /**
     * Sends a POST request to add a new child to the parent's profile.
     *
     * @param childName the name of the child
     * @param dob the date of birth of the child
     */
    // Scenario: Parent able to add a child to their profile for tracking vaccines and medication
    @When("the parent adds a child with name {string} and date of birth {string}")
    public void theParentAddsAChildWithNameAndDateOfBirth(String childName, String dob) {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The parent adds a child");
        try {
            RequestSpecification request = RestAssured.given();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("childName", childName);
            jsonObject.put("dateOfBirth", dob);

            response = request.contentType(ContentType.JSON).body(jsonObject.toString()).post(BASE_URL + port + childrenEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    /**
     * Validates that the child was successfully added to the parent's profile.
     */
    @Then("the child should be successfully added to the parent's profile")
    public void theChildShouldBeSuccessfullyAddedToTheParentSProfile() {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The child is successfully added");
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getString("childId"));
    }


    /**
     * Sends a GET request to view the list of children associated with the parent's profile.
     */
    // Scenario: Parent able to view a list of their children
    @When("the parent views the list of their children")
    public void theParentViewsTheListOfTheirChildren() {
        logger.info("Scenario: Parent able to view a list of their children - Step: The parent views the list");
        try {
            RequestSpecification request = RestAssured.given();
            response = request.contentType(ContentType.JSON).get(BASE_URL + port + childrenEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    /**
     * Validates that the specified child's name is included in the list of children.
     *
     * @param childName the name of the child
     */
    @Then("the list should include {string}")
    public void theListShouldInclude(String childName) {
        logger.info("Scenario: Parent able to view a list of their children - Step: The list should include child");
        JsonPath jsonPath = response.jsonPath();
        List<String> childNames = jsonPath.getList("children.name");
        Assert.assertTrue(childNames.contains(childName));
    }


    /**
     * Verifies that the specified child exists in the parent's profile.
     *
     * @param childName the name of the child
     */
    // Scenario: Parent able to edit a child's details
    @Given("the child {string} exists in the parent's profile")
    public void theChildExistsInTheParentsProfile(String childName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: Check if child exists");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + port + childrenEndpoint);

        List<String> childNames = response.jsonPath().getList("children.name");
        Assert.assertTrue("Child does not exist in the parent's profile", childNames.contains(childName));
    }

    /**
     * Sends a PUT request to edit the name of the child in the parent's profile.
     *
     * @param newName the new name for the child
     */
    @When("the parent edits the child's name to {string}")
    public void theParentEditsTheChildSNameTo(String newName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: The parent edits the child's name");
        try {
            RequestSpecification request = RestAssured.given();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("newName", newName);

            response = request.contentType(ContentType.JSON).body(jsonObject.toString()).put(BASE_URL + port + childEndpoint);
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    /**
     * Validates that the child's name was successfully updated in the parent's profile.
     *
     * @param newName the new name for the child
     */
    @Then("the child's name should be updated to {string}")
    public void theChildSNameShouldBeUpdatedTo(String newName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: The child's name is updated");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newName, response.jsonPath().getString("newName"));
    }


    /**
     * Sends a GET request to view the profile of the specified child.
     *
     * @param childName the name of the child
     */
    // Scenario: Parent able to view a child's profile
    @When("the parent views the profile of {string}")
    public void theParentViewsTheProfileOf(String childName) {
        // Implementation for viewing a child's profile
        logger.info("Scenario: Parent able to view a child's profile - Step: The parent views the profile");
        try {
            RequestSpecification request = RestAssured.given();
            response = request.contentType(ContentType.JSON).get(BASE_URL + port + childEndpoint.replace("{child_name}", childName));
        } catch (Exception e) {
            logger.warning("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception");
        }
    }

    /**
     * Validates that the child's profile displays the correct name and date of birth.
     *
     * @param childName the name of the child
     * @param dob the date of birth of the child
     */
    @Then("the profile should display the name {string} and date of birth {string}")
    public void theProfileShouldDisplayTheNameAndDateOfBirth(String childName, String dob) {
        logger.info("Scenario: Parent able to view a child's profile - Step: The profile displays the correct details");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(childName, jsonPath.getString("name"));
        Assert.assertEquals(dob, jsonPath.getString("dateOfBirth"));
    }
}
