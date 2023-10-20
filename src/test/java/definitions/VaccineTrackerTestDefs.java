package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.logging.Logger;

public class VaccineTrackerTestDefs extends TestSetupDefs{

    private static final Logger logger = Logger.getLogger(AuthenticationTestDefs.class.getName());

    private static Response response;
    private static RequestSpecification request;


    // Scenario: Retrieve pre-loaded list of vaccines from database
    @Given("the system has a list of standard vaccines for newborns in the database")
    public void theSystemHasAListOfStandardVaccinesForNewbornsInTheDatabase() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: The system has a list of standard vaccines for newborns in the database");
        request = RestAssured.given();
    }

    @When("a request is made to fetch the list of vaccines")
    public void aRequestIsMadeToFetchTheListOfVaccines() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: A request is made to fetch the list of vaccines");
        response = request.get(allVaccinesEndpoint);
    }

    @Then("the system should return the pre-loaded list of standard vaccines")
    public void theSystemShouldReturnThePreLoadedListOfStandardVaccines() {
        logger.info("Scenario: Retrieve pre-loaded list of vaccines from database - Step: The system should return the pre-loaded list of standard vaccines");
        response.then().statusCode(200);
    }


    // Scenario: Update a vaccine as administered in the database
    @Given("the system has a list of vaccines")
    public void theSystemHasAListOfVaccines() {

    }

    @When("the {string} action is triggered for a specific vaccine with a given date")
    public void theActionIsTriggeredForASpecificVaccineWithAGivenDate(String arg0) {

    }

    @Then("that vaccine's status should be updated to {string} with the specified date in the database")
    public void thatVaccineSStatusShouldBeUpdatedToWithTheSpecifiedDateInTheDatabase(String arg0) {

    }


    // Scenario: Identify past due vaccines
    @Given("the system has a list of vaccines with due dates")
    public void theSystemHasAListOfVaccinesWithDueDates() {

    }

    @When("the current date is checked")
    public void theCurrentDateIsChecked() {

    }

    @Then("the system should identify which vaccines are past due")
    public void theSystemShouldIdentifyWhichVaccinesArePastDue() {
    }
}
