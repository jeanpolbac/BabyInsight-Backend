package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class InteractionCheckerTestDefs extends TestSetupDefs {
    private static final Logger logger = Logger.getLogger(InteractionCheckerTestDefs.class.getName());
    private static Response response;

    @And("I have access to the Drug Interaction API with RXCUIs {string} and sources {string}")
    public void iHaveAccessToTheDrugInteractionAPI(String rxcuis, String sources) {
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

    @When("I send a request to check interactions among {string}")
    public void iSendARequestToCheckInteractionsAmong(String medicationList) {
        String nhiBaseUrl = "https://rxnav.nlm.nih.gov";
        String[] medications = medicationList.split(", ");
        StringBuilder apiRequest = new StringBuilder( nhiBaseUrl + "/REST/interaction/interaction.json?");
        for (String medication : medications) {
            apiRequest.append("rxcui=").append(medication).append("&");
        }
        apiRequest.deleteCharAt(apiRequest.length() - 1);

        try {
            response = RestAssured.get(apiRequest.toString());
            logger.info("Interaction check request successful. URL: " + apiRequest.toString());
        } catch (Exception e) {
            logger.severe("Failed to check interactions. URL: " + apiRequest.toString() + " Error: " + e.getMessage());
        }
    }

    @Then("the application should return {string}")
    public void theApplicationShouldReturn(String expectedResponse) {
        try {
            assertEquals(expectedResponse, response.asString());
            logger.info("Response validation successful. Expected: " + expectedResponse + ", Actual: " + response.asString());
        } catch (Exception e) {
            logger.severe("Response validation failed. Expected: " + expectedResponse + ", Actual: " + response.asString() + " Error: " + e.getMessage());
        }
    }
}






