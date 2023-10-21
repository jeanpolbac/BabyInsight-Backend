package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * This class defines the Cucumber step definitions for testing drug interaction API.
 */
public class InteractionCheckerTestDefs extends TestSetupDefs {
    private static final Logger logger = Logger.getLogger(InteractionCheckerTestDefs.class.getName());
    private static Response response;


    /**
     * Initializes the API request for drug interactions with specified RXCUIs and sources
     *
     * @param rxcuis  The RXCUIs of the drugs to check for interactions
     * @param sources The sources to use for checking interactions
     */
    @And("I have access to the Drug Interaction API with RXCUIs {string} and sources {string}")
    public void iHaveAccessToTheDrugInteractionAPI(String rxcuis, String sources) {
        logger.info("Scenario: Check interactions between medications - Step: I have access to the Drug Interaction API with RXCUIs and sources");
        String nhiBaseUrl = "https://rxnav.nlm.nih.gov";
        String endpoint = "/REST/interaction/list.json";
        String apiUrl = nhiBaseUrl + endpoint + "?rxcuis=" + rxcuis + "&sources=" + sources;

        try {
            response = RestAssured.get(apiUrl);
            logger.info("API request successful. URL: " + apiUrl);
        } catch (Exception e) {
            logger.severe("Failed to make API request. URL: " + apiUrl + " Error: " + e.getMessage());
        }
    }

    /**
     * Sends a request to the API to check for interactions among the specified medication list
     *
     * @param medicationList A string containing the medication list, with medications separated by commas
     */
    @When("I send a request to check interactions among {string}")
    public void iSendARequestToCheckInteractionsAmong(String medicationList) {
        logger.info("Scenario: Check interactions between medications - Step: I send a request to check interactions among medications");
        String nhiBaseUrl = "https://rxnav.nlm.nih.gov";
        String[] medications = medicationList.split(", ");
        StringBuilder apiRequest = new StringBuilder( nhiBaseUrl + "/REST/interaction/interaction.json?");
        for (String medication : medications) {
            apiRequest.append("rxcui=").append(medication).append("&"); // Build the API request URL with each medication's RXCUI
        }
        apiRequest.deleteCharAt(apiRequest.length() - 1);

        try {
            response = RestAssured.get(apiRequest.toString());
            logger.info("Interaction check request successful. URL: " + apiRequest.toString());
        } catch (Exception e) {
            logger.severe("Failed to check interactions. URL: " + apiRequest.toString() + " Error: " + e.getMessage());
        }
    }

    /**
     * Validates the API response against the expected response.
     *
     * @param expectedResponse The expected response string.
     */
    @Then("the application should return {string}")
    public void theApplicationShouldReturn(String expectedResponse) {
        logger.info("Scenario: Check interactions between medications - Step: the application should return");
        try {
            assertEquals(expectedResponse, response.asString());
            logger.info("Response validation successful. Expected: " + expectedResponse + ", Actual: " + response.asString());
        } catch (Exception e) {
            logger.severe("Response validation failed. Expected: " + expectedResponse + ", Actual: " + response.asString() + " Error: " + e.getMessage());
        }
    }
}






