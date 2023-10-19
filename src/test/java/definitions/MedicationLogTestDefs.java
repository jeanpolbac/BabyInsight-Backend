package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.logging.Logger;

public class MedicationLogTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());

    private static Response response;


    // Scenario: Add new medication with all required fields
    @Given("I am on the medication log page")
    public void iAmOnTheMedicationLogPage() {
    }

    @When("I fill in the fields for medication name, dosage, frequency, and duration")
    public void iFillInTheFieldsForMedicationNameDosageFrequencyAndDuration() {

    }

    @Then("the new medication should appear in the list of all active medications")
    public void theNewMedicationShouldAppearInTheListOfAllActiveMedications() {

    }

    // Scenario: View list of all active medications
    @Given("I have added medications to the log")
    public void iHaveAddedMedicationsToTheLog() {

    }

    @When("I visit the medication log page")
    public void iVisitTheMedicationLogPage() {

    }

    @Then("I should see a list of all active medications")
    public void iShouldSeeAListOfAllActiveMedications() {

    }

    // Scenario: Archive completed or expired medications
    @Given("I have completed or expired medications in the active list")
    public void iHaveCompletedOrExpiredMedicationsInTheActiveList() {

    }

    @When("I click the {string} button next to a completed or expired medication")
    public void iClickTheButtonNextToACompletedOrExpiredMedication(String arg0) {

    }

    @Then("that medication should be removed from the active list")
    public void thatMedicationShouldBeRemovedFromTheActiveList() {

    }

    @And("it should be moved to the archived list")
    public void itShouldBeMovedToTheArchivedList() {
    }
}
