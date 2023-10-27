package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * The MedicationLogTestDefs class contains step definitions for Cucumber scenarios
 * related to the medication log page functionality.
 */
public class MedicationLogTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(MedicationLogTestDefs.class.getName());
    private static Response response;
    private static RequestSpecification request;
    private String medName;


    /**
     * Verifies the user is on the medication log page by making a GET request to the
     * child medications endpoint and checking for a 200 OK status code.
     */
    // Scenario: Add new medication with all required fields
    @When("I am on the medication log page")
    public void iAmOnTheMedicationLogPage() {
        logger.info("Scenario: Add new medication with all required fields - Step: I am on the medication log page");
        try {
            response = RestAssured.get(childMedicationsEndpoint);
            assertEquals(200, response.getStatusCode());
        } catch (Exception e) {
            logger.severe("Error fetching list of active medications: " + e.getMessage());
        }
    }

    /**
     * Fills in the medication details and sends a POST request to add a new medication.
     */
    @And("I fill in the fields for medication {string}, {string}, {string}, and {string}")
    public void iFillInTheFieldsForMedicationAnd(String medName, String dosage, String frequency, String duration) {
        logger.info("Scenario: Add new medication with all required fields - Step: I fill in the fields for medication");
        try {
            // Build the request specification with the medication details
            request = RestAssured.given()
                    .param("medicationName", medName)
                    .param("dosage", dosage)
                    .param("frequency", frequency)
                    .param("duration", duration);

            // Send POST request to add the new medication
            response = request.post(addMedicationEndpoint);
            assertEquals(201, response.getStatusCode());

            // Store the medication name for later verification
            this.medName = medName;
    } catch (Exception e) {
            logger.severe("Error adding new medication: " + e.getMessage());
        }
    }

    /**
     * Verifies the newly added medication appears in the list of all active medications.
     */
    @Then("the new medication should appear in the list of all active medications")
    public void theNewMedicationShouldAppearInTheListOfAllActiveMedications() {
        logger.info("Scenario: Add new medication with all required fields - Step: The new medication should appear in the list of all active medications");
        try {
            List<String> medicationsList = response.jsonPath().getList("medications.name");
            assertTrue(medicationsList.contains(this.medName));
        } catch (Exception e) {
            logger.severe("Error fetching list of active medications: " + e.getMessage());
        }
    }


    /**
     * Verifies the user can view the list of all active medications by sending a GET request
     * to the child medications endpoint and checking for a 200 OK status code.
     */
    // Scenario: View list of all active medications
    @When("I visit the medication log page")
    public void iVisitTheMedicationLogPage() {
        logger.info("Scenario: View list of all active medications - Step: I visit the medication log page");
        try {
            response = RestAssured.get(childMedicationsEndpoint);
            assertEquals(200, response.getStatusCode());
        } catch (Exception e) {
            logger.severe("Error fetching list of active medications: " + e.getMessage());
        }
    }

    /**
     * Verifies that a list of all active medications is returned and is not empty.
     */
    @Then("I should see a list of all active medications")
    public void iShouldSeeAListOfAllActiveMedications() {
        logger.info("Scenario: View list of all active medications - Step: I should see a list of all active medications");
        try {
            List<String> medicationsList = response.jsonPath().getList("medications.name");
            assertFalse(medicationsList.isEmpty());
        } catch (Exception e) {
            logger.severe("Error fetching list of active medications: " + e.getMessage());
        }
    }


    /**
     * Ensures the user has a list of active medications for a specified child by sending
     * a GET request to the child medications endpoint and checking for a 200 OK status code.
     */
    // Scenario: Archive completed or expired medications
    @Given("I have a list of active medications for a child with ID {string}")
    public void iHaveAListOfActiveMedicationsForAChild(String childID) {
        logger.info("Scenario: Archive completed or expired medications - Step: I have a list of active medications for a child with ID");
        try {
            response = RestAssured.get(childMedicationsEndpoint.replace("{childID}", childID));
            assertEquals(200, response.getStatusCode());
        } catch (Exception e) {
            logger.severe("Error fetching list of active medications: " + e.getMessage());
        }
    }

    /**
     * Archives a specified medication by sending a PUT request to update its status
     * to archived and checking for a 200 OK status code.
     */
    @When("I archive a medication with ID {string}")
    public void iArchiveAMedication(String medicationID) {
        logger.info("Scenario: Archive completed or expired medications - Step: I archive a medication with ID");
        try {
            // Sending a PUT request to update the status of the medication to archived
            response = RestAssured.put(specificMedicationEndpoint.replace("{medicationID}", medicationID));
            assertEquals(200, response.getStatusCode());
        } catch (Exception e) {
            logger.severe("Error archiving medication: " + e.getMessage());
        }
    }

    /**
     * Verifies the archived medication appears in the list of archived medications
     * by sending a GET request to the archived medications endpoint and checking for
     * the presence of the medication ID in the list of archived medications.
     */
    @Then("the medication with ID {string} should be in the archived list")
    public void theMedicationShouldBeInTheArchivedList(String medicationID) {
        logger.info("Scenario: Archive completed or expired medications - Step: the medication with ID {string} should be in the archived list");
        try {
            // Sending a GET request to fetch the list of archived medications
            response = RestAssured.get(archivedMedicationsEndpoint);
            // Extracting the list of medication IDs from the response
            List<String> archivedMedicationsList = response.jsonPath().getList("medications.id");
            // Verifying the archived medication ID is present in the list
            assertTrue(archivedMedicationsList.contains(medicationID));
        } catch (Exception e) {
            logger.severe("Error fetching list of archived medications: " + e.getMessage());
        }
    }
}
