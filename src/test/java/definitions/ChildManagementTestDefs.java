package definitions;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.UserRepository;
import com.example.babyinsightbackend.service.ChildService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * The ChildManagementTestDefs class contains step definitions for Cucumber scenarios
 * related to managing child profiles by a parent.
 */
public class ChildManagementTestDefs extends TestSetupDefs {

    private static final Logger logger = Logger.getLogger(ChildManagementTestDefs.class.getName());
    private static Response response;
    private User user = new User(1L, "userEmail", "userPassword");
    @Autowired
    private ChildService childService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChildRepository childRepository;
    private static Child createdChild;
    private static List<Child> childList;
    private Child fetchedChild;



    /**
     * Sends a POST request to add a new child to the parent's profile.
     *
     * @param childName the name of the child
     * @param dob the date of birth of the child
     */
    // Scenario: Parent able to add a child to their profile for tracking vaccines and medication
    @When("the parent adds a child with name {string} and date of birth {string}")
    public void theParentAddsAChildWithNameAndDateOfBirth(String childName, String dob) {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The parent adds a child");
        Child child = new Child();
        child.setName(childName);
        child.setDateOfBirth(LocalDate.parse(dob));
        child.setUser(user);
        user.getChildren().add(child);

        userRepository.save(user);
        childRepository.save(child);

        createdChild = child;
        logger.info("Scenario 1 - Step 1 - Created Child: " + createdChild);
    }

    /**
     * Validates that the child was successfully added to the parent's profile.
     */
    @Then("the child should be successfully added to the parent's profile")
    public void theChildShouldBeSuccessfullyAddedToTheParentSProfile() {
        logger.info("Scenario: Parent able to add a child to their profile - Step: The child is successfully added");
        logger.info("Created Child: " + createdChild);
        assertNotNull(createdChild);
        assertNotNull(createdChild.getId());
        assertEquals(user.getId(), createdChild.getUser().getId());
        logger.info("Scenario 1 - Step 2 - Created Child: " + createdChild.toString());
    }


    /**
     * Sends a GET request to view the list of children associated with the parent's profile.
     */
    // Scenario: Parent able to view a list of their children
    @When("the parent views the list of their children")
    public void theParentViewsTheListOfTheirChildren() {
        logger.info("Scenario: Parent able to view a list of their children - Step: The parent views the list");
        childList = childService.getAllChildrenByParentId(user.getId());
        assertTrue(childList.contains(createdChild));
        logger.info("Scenario 2 - Step 1: " + createdChild);
    }

    /**
     * Validates that the specified child's name is included in the list of children.
     *
     * @param childName the name of the child
     */
    @Then("the list should include {string}")
    public void theListShouldInclude(String childName) {
        logger.info("Scenario: Parent able to view a list of their children - Step: The list should include child");
        assertTrue(childList.stream().anyMatch(child -> child.getName().equals(childName)));
        logger.info("Scenario 2 - Step 2: " + childList);
    }


    /**
     * Verifies that the specified child exists in the parent's profile.
     *
     * @param childName the name of the child
     */
    // Scenario: Parent able to edit a child's details
    @Given("the child {string} exists in the parent's profile")
    public void theChildExistsInTheParentsProfile(String childName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: Check if child exists");
        logger.info("Child List GIVEN: " + childList);
        assertEquals(childName, createdChild.getName());
        logger.info("Scenario 3 - Step 1: " + createdChild);
    }

    /**
     * Sends a PUT request to edit the name of the child in the parent's profile.
     *
     * @param newName the new name for the child
     */
    @When("the parent edits the child's name to {string}")
    public void theParentEditsTheChildSNameTo(String newName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: The parent edits the child's name");
        assertNotNull(createdChild);
        createdChild.setName(newName);
        childService.updateChild(createdChild.getId(), createdChild);
        logger.info("Scenario 3 - Step 2: " + createdChild);
    }

    /**
     * Validates that the child's name was successfully updated in the parent's profile.
     *
     * @param updatedName the new name for the child
     */
    @Then("the child's name should be updated to {string}")
    public void theChildSNameShouldBeUpdatedTo(String updatedName) {
        logger.info("Scenario: Parent able to edit a child's details - Step: The child's name is updated");
        Child updatedChild = childService.getChildById(createdChild.getId()).orElse(null);
        assertNotNull(updatedChild);
        assertEquals(updatedName, updatedChild.getName());
        logger.info("Scenario 3 - Step 3: " + createdChild);
    }


    /**
     * Sends a GET request to view the profile of the specified child.
     *
     * @param childName the name of the child
     */
    // Scenario: Parent able to view a child's profile
    @When("the parent views the profile of {string}")
    public void theParentViewsTheProfileOf(String childName) {
        // Implementation for viewing a child's profile
        logger.info("Scenario: Parent able to view a child's profile - Step: The parent views the profile");
        fetchedChild = childService.getChildByName(childName).orElse(null);
        logger.info("Scenario 4 - Step 1: " + fetchedChild);
    }

    /**
     * Validates that the child's profile displays the correct name and date of birth.
     *
     * @param childName the name of the child
     * @param dob the date of birth of the child
     */
    @Then("the profile should display the name {string} and date of birth {string}")
    public void theProfileShouldDisplayTheNameAndDateOfBirth(String childName, String dob) {
        logger.info("Scenario: Parent able to view a child's profile - Step: The profile displays the correct details");
        assertNotNull(fetchedChild);
        assertEquals(childName, fetchedChild.getName());
        assertEquals(LocalDate.parse(dob), fetchedChild.getDateOfBirth());
        logger.info("Scenario 4 - Step 2: " + fetchedChild);
    }
}
