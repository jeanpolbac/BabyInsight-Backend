package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthenticationTestDefs extends TestSetupDefs {

    //Scenario: User able to access public endpoints
    @Given("a valid public endpoint")
    public void aValidPublicEndpoint() {
    }

    @When("I say hello")
    public void iSayHello() {
    }

    @Then("{string} is shown")
    public void isShown(String arg0) {
    }

    // Scenario: Successful registration
    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {

    }

    //Scenario: User able to login and receive jwt token
    @When("I enter a valid email address and a strong password")
    public void iEnterAValidEmailAddressAndAStrongPassword() {

    }

    @Then("I should be successfully registered")
    public void iShouldBeSuccessfullyRegistered() {

    }

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
