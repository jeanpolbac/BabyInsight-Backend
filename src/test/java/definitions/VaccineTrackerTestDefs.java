package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import java.util.logging.Logger;

/**
 * The VaccineTrackerTestDefs class contains step definitions for Cucumber scenarios
 * related to vaccine tracking and retrieval.
 */
public class VaccineTrackerTestDefs extends TestSetupDefs{

    private static final Logger logger = Logger.getLogger(VaccineTrackerTestDefs.class.getName());
    private static Response response;
    private static RequestSpecification request;


    /**
     * Ensures that the system has a pre-loaded list of standard vaccines for newborns.
     */
    // Scenario: Retrieve pre-loaded list of vaccines
    @Given("the system has a list of standard vaccines for newborns in the database")
    public void theSystemHasAListOfStandardVaccinesForNewbornsInTheDatabase() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: The system has a list of standard vaccines for newborns in the database");
        request = RestAssured.given();
    }

    /**
     * Sends a request to fetch the list of vaccines.
     */
    @When("a request is made to fetch the list of vaccines")
    public void aRequestIsMadeToFetchTheListOfVaccines() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: A request is made to fetch the list of vaccines");
        try {
            response = request.get(allVaccinesEndpoint);
            logger.info("Received response with status code: " + response.statusCode());
        } catch (Exception e) {
            logger.severe("An error occurred while fetching the list of vaccines: " + e.getMessage());
        }
    }

    /**
     * Validates the response to ensure the pre-loaded list of standard vaccines is returned.
     */
    @Then("the system should return the pre-loaded list of standard vaccines")
    public void theSystemShouldReturnThePreLoadedListOfStandardVaccines() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: The system should return the pre-loaded list of standard vaccines");
        response.then().statusCode(200);
    }


    /**
     * Ensures that the system has a record of vaccines administered to a specified child.
     */
    // Scenario: Retrieve list of administered vaccines for a child
    @Given("the system has a record of vaccines administered to a child")
    public void theSystemHasARecordOfVaccinesAdministeredToAChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: The system has a record of vaccines administered to a child");
        request = RestAssured.given();
    }

    /**
     * Sends a request to fetch the list of administered vaccines for the specified child.
     */
    @When("a request is made to fetch the list of administered vaccines for the child")
    public void aRequestIsMadeToFetchTheListOfAdministeredVaccinesForTheChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: A request is made to fetch the list of administered vaccines for the child");
        try {
            response = request.get(childVaccinesEndpoint);
            logger.info("Received response with status code: " + response.statusCode());
        } catch (Exception e) {
            logger.severe("Error fetching list of administered vaccines for the child: " + e.getMessage());
        }
    }

    /**
     * Validates the response to ensure the list of administered vaccines is returned.
     */
    @Then("the system should return the list of vaccines administered to the child")
    public void theSystemShouldReturnTheListOfVaccinesAdministeredToTheChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: The system should return the list of vaccines administered to the child");
        response.then().statusCode(200);
    }


    /**
     * Ensures that the system has a record of vaccines to be administered to a specified child.
     */
    // Scenario: Retrieve list of remaining vaccines for a child
    @Given("the system has a record of vaccines to be administered to a child")
    public void theSystemHasARecordOfVaccinesToBeAdministeredToAChild() {
        logger.info("Scenario: Retrieve list of remaining vaccines for a child - Step: The system has a record of vaccines to be administered to a child");
        request = RestAssured.given();
    }

    /**
     * Sends a request to fetch the list of remaining vaccines for the specified child.
     */
    @When("a request is made to fetch the list of remaining vaccines for the child")
    public void aRequestIsMadeToFetchTheListOfRemainingVaccinesForTheChild() {
        logger.info("Scenario: Retrieve list of remaining vaccines for a child - Step: A request is made to fetch the list of remaining vaccines for the child");
        try {
            response = request.get(remainingVaccinesEndpoint);
            logger.info("Received response with status code: " + response.statusCode());
        } catch (Exception e) {
            logger.severe("Error fetching list of remaining vaccines for the child: " + e.getMessage());
        }
    }

    /**
     * Validates the response to ensure the list of remaining vaccines is returned.
     */
    @Then("the system should return the list of vaccines yet to be administered to the child")
    public void theSystemShouldReturnTheListOfVaccinesYetToBeAdministeredToTheChild() {
        logger.info("Scenario: Retrieve list of remaining vaccines for a child - Step: The system should return the list of vaccines yet to be administered to the child");
        response.then().statusCode(200);
    }


    /**
     * Triggers the specified action for a specified vaccine on a specified date.
     * @param action The action to be triggered.
     * @param vaccine The vaccine the action is being triggered for.
     * @param date The date the action is triggered.
     */
    // Scenario: Update a vaccine as administered in the database
    @When("the {string} action is triggered for {string} vaccine with a date of {string}")
    public void theActionIsTriggeredForVaccineWithADateOf(String action, String vaccine, String date) {
        logger.info("Scenario: Update a vaccine as administered in the database - Step: The " + action + " action is triggered for " + vaccine + " vaccine with a date of " + date);
        try {
            request = RestAssured.given().param("action", action).param("date", date);
            response = request.put(specificChildVaccineEndpoint);
            if (response != null) {
                logger.info("Received response with status code: " + response.statusCode());
            } else {
                logger.warning("No response received from the server.");
            }
        } catch (Exception e) {
            logger.severe("An error occurred while triggering action for the vaccine: " + e.getMessage());
        }
    }

    /**
     * Validates the vaccine status and administered date are updated as expected.
     * @param vaccineName The name of the vaccine.
     * @param status The updated status of the vaccine.
     * @param date The date the vaccine was administered.
     */
    @Then("{string} status should be updated to {string} with the date {string}")
    public void statusShouldBeUpdatedToWithTheDate(String vaccineName, String status, String date) {
        logger.info("Scenario: Update a vaccine as administered in the database - Step: That vaccine's status should be updated to " + status + " with the date " + date);
        try {
            response.then().statusCode(200)
                    .body("status", equalTo(status))
                    .body("vaccineName", equalTo(vaccineName))
                    .body("administeredDate", equalTo(date));
        } catch (Exception e) {
            logger.severe("An error occurred while updating vaccine status: " + e.getMessage());
        }
    }
}
