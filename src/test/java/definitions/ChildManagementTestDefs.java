package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.logging.Logger;

public class ChildManagementTestDefs extends TestSetupDefs{

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());

    private static Response response;

    @Given("the parent is logged in with credentials {string} and password {string}")
    public void theParentIsLoggedInWithCredentialsAndPassword(String emailAddress, String password) {
    }

    @When("the parent adds a child with name {string} and date of birth {string}")
    public void theParentAddsAChildWithNameAndDateOfBirth(String childName, String dob) {
        
    }

    @Then("the child should be successfully added to the parent's profile")
    public void theChildShouldBeSuccessfullyAddedToTheParentSProfile() {
        
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
