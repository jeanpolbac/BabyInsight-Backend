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


    // Scenario: Retrieve list of administered vaccines for a child
    @Given("the system has a record of vaccines administered to a child")
    public void theSystemHasARecordOfVaccinesAdministeredToAChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: The system has a record of vaccines administered to a child");
        request = RestAssured.given();
    }

    @When("a request is made to fetch the list of administered vaccines for the child")
    public void aRequestIsMadeToFetchTheListOfAdministeredVaccinesForTheChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: A request is made to fetch the list of administered vaccines for the child");
        response = request.get(listChildAdministeredVaccinesEndpoint);
    }

    @Then("the system should return the list of vaccines administered to the child")
    public void theSystemShouldReturnTheListOfVaccinesAdministeredToTheChild() {
        logger.info("Scenario: Retrieve list of administered vaccines for a child - Step: The system should return the list of vaccines administered to the child");
        response.then().statusCode(200);
    }


    // Scenario: Retrieve list of remaining vaccines for a child
    @Given("the system has a record of vaccines to be administered to a child")
    public void theSystemHasARecordOfVaccinesToBeAdministeredToAChild() {

    }

    @When("a request is made to fetch the list of remaining vaccines for the child")
    public void aRequestIsMadeToFetchTheListOfRemainingVaccinesForTheChild() {

    }

    @Then("the system should return the list of vaccines yet to be administered to the child")
    public void theSystemShouldReturnTheListOfVaccinesYetToBeAdministeredToTheChild() {
    }


    // Scenario: Update a vaccine as administered in the database
    @Given("the system has a list of vaccines")
    public void theSystemHasAListOfVaccines() {
        logger.info("Scenario: Update a vaccine as administered in the database - Step: The system has a list of vaccines");
        request = RestAssured.given();
    }

    @When("the {string} action is triggered for a specific vaccine with a given date")
    public void theActionIsTriggeredForASpecificVaccineWithAGivenDate(String action) {
        logger.info("Scenario: Update a vaccine as administered in the database - Step: The " + action + " action is triggered for a specific vaccine with a given date");
        response = request.put(specificChildVaccineEndpoint);
    }

    @Then("that vaccine's status should be updated to {string} with the specified date in the database")
    public void thatVaccineSStatusShouldBeUpdatedToWithTheSpecifiedDateInTheDatabase(String status) {
        logger.info("Scenario: Update a vaccine as administered in the database - Step: That vaccine's status should be updated to " + status + " with the specified date in the database");
        response.then().statusCode(200);
    }


    // Scenario: Identify past due vaccines
    @Given("the system has a list of vaccines with due dates")
    public void theSystemHasAListOfVaccinesWithDueDates() {
        logger.info("Scenario: Identify past due vaccines - Step: The system has a list of vaccines with due dates");
        request = RestAssured.given();
    }

    @When("the current date is checked")
    public void theCurrentDateIsChecked() {
        logger.info("Scenario: Identify past due vaccines - Step: The current date is checked");
    }

    @Then("the system should identify which vaccines are past due")
    public void theSystemShouldIdentifyWhichVaccinesArePastDue() {
        logger.info("Scenario: Identify past due vaccines - Step: The system should identify which vaccines are past due");
        response = request.get(overdueVaccinesEndpoint);
    }
}
