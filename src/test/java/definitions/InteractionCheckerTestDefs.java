package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InteractionCheckerTestDefs extends TestSetupDefs{

    // Scenario: Input two medications and find interactions
    @Given("I have a predefined list of medication interactions")
    public void iHaveAPredefinedListOfMedicationInteractions() {
    }

    @When("I send a request to check interactions between {string} and {string}")
    public void iSendARequestToCheckInteractionsBetweenAnd(String arg0, String arg1) {

    }

    @Then("the application should return the interaction details if found")
    public void theApplicationShouldReturnTheInteractionDetailsIfFound() {

    }


    // Scenario: Input multiple medications and find interactions
    @When("I send a request to check interactions among {string}, {string}, and {string}")
    public void iSendARequestToCheckInteractionsAmongAnd(String arg0, String arg1, String arg2) {

    }

    @Then("the application should return a confirmation message stating no interactions found")
    public void theApplicationShouldReturnAConfirmationMessageStatingNoInteractionsFound() {

    }



    // Scenario: Input medications with no interactions
    @When("I send a request to check interactions for {string} only")
    public void iSendARequestToCheckInteractionsForOnly(String arg0) {

    }

    @Then("the application should return an error message stating that at least two medications are required for interaction check")
    public void theApplicationShouldReturnAnErrorMessageStatingThatAtLeastTwoMedicationsAreRequiredForInteractionCheck() {
    }
}
