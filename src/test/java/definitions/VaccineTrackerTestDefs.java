package definitions;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.models.Vaccine;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.UserRepository;
import com.example.babyinsightbackend.repository.VaccineRepository;
import com.example.babyinsightbackend.service.ChildService;
import com.example.babyinsightbackend.service.VaccineService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The VaccineTrackerTestDefs class contains step definitions for Cucumber scenarios
 * related to vaccine tracking and retrieval.
 */
public class VaccineTrackerTestDefs extends TestSetupDefs{

    private static final Logger logger = Logger.getLogger(VaccineTrackerTestDefs.class.getName());
    private static Response response;

    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private ChildService childService;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChildRepository childRepository;
    private List<Vaccine> administeredVaccines;

    private List<Vaccine> fetchedAdministeredVaccines;

    private Vaccine newVaccine;
    private Vaccine createdVaccine;
    private List<Vaccine> existingVaccines;
    private List<Vaccine> retrieveVaccineList;
    private Optional<Vaccine> retrievedVaccine;

    private static Child child;
    private Long vaccineId;




    @Given("a new vaccine with name {string}")
    public void aNewVaccineWithName(String vaccineName) {
        newVaccine = new Vaccine();
        newVaccine.setName(vaccineName);
    }

    @When("the vaccine is added to the system")
    public void theVaccineIsAddedToTheSystem() {
        createdVaccine = vaccineService.addVaccine(newVaccine, child);
    }

    @Then("the system should return the created vaccine")
    public void theSystemShouldReturnTheCreatedVaccine() {
        Assert.assertNotNull(createdVaccine);
        assertEquals(newVaccine.getName(), createdVaccine.getName());
    }


    @Given("there are existing vaccines in the system for a child at {int} months")
    public void thereAreExistingVaccinesInTheSystem(int childAgeInMonths) {
        existingVaccines = vaccineService.getRequiredVaccinesForChildByAge(childAgeInMonths);
        logger.info("Existing vaccines: " + existingVaccines);
    }

    @When("I retrieve all vaccines")
    public void iRetrieveAllVaccines() {
        retrieveVaccineList = vaccineService.getAllVaccines();
        logger.info("Retrieved vaccines: " + retrieveVaccineList);

    }

    @Then("I should get a list of vaccines")
    public void iShouldGetAListOfVaccines() {
        Assert.assertNotNull(retrieveVaccineList);
        logger.info("Retrieved vaccines: " + retrieveVaccineList);
    }


    @Given("there is a vaccine with ID {int} in the system")
    public void thereIsAVaccineWithIDInTheSystem(int vaccineId) {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineId((long) vaccineId);
        vaccineService.addVaccine(vaccine, child);
        this.vaccineId = (long) vaccineId;
    }

    @When("I retrieve the vaccine with ID {int}")
    public void iRetrieveTheVaccineWithID(int vaccineId) {
        retrievedVaccine = vaccineService.getVaccineById((long) vaccineId);
        logger.info("Retrieved vaccine: " + retrievedVaccine);
    }

    @Then("I should get the vaccine details")
    public void iShouldGetTheVaccineDetails() {
        Assert.assertFalse(retrievedVaccine.isEmpty());
        boolean idMatch = retrievedVaccine.stream()
                .anyMatch(vaccine -> vaccine.getVaccineId().equals(this.vaccineId));
        Assert.assertTrue(idMatch);
    }


    @Given("the system has a record of vaccines administered to a child")
    public void theSystemHasARecordOfVaccinesAdministeredToAChildUsingTheServiceLayer() {

        User user = new User();
        user.setEmailAddress("testuser@example.com");
        user.setPassword("password123");


        user = userRepository.save(user);

        Child child = new Child();
        child.setName("Boba Fett");
        child.setDateOfBirth(LocalDate.now());
        child.setUser(user);

        logger.info("Created child: " + child);

        child = childRepository.save(child);

        if (child.getId() == null) {
            fail("Child ID not generated!");
        }

        administeredVaccines = new ArrayList<>();

        Vaccine vaccine1 = new Vaccine(null, "Vaccine 1", 2, LocalDate.now());
        vaccineService.addVaccine(vaccine1, child);
        administeredVaccines.add(vaccine1);

        Vaccine vaccine2 = new Vaccine(null, "Vaccine 2", 0, LocalDate.now());
        vaccineService.addVaccine(vaccine2, child);
        administeredVaccines.add(vaccine2);

        Vaccine vaccine3 = new Vaccine(null, "Vaccine 3", 4, LocalDate.now());
        vaccineService.addVaccine(vaccine3, child);
        administeredVaccines.add(vaccine3);


        logger.info("Created administered vaccines: " + child.getAdministeredVaccines());
    }



    @When("a request is made to fetch the list of administered vaccines for the child")
    public void aRequestIsMadeToFetchTheListOfAdministeredVaccinesForTheChild() {
        logger.info("Fetching administered vaccines for child: " + child);
        Child child = childRepository.findById(1L).orElse(null);
        fetchedAdministeredVaccines = vaccineRepository.findByChild(child);
        logger.info("Fetched administered vaccines: " + fetchedAdministeredVaccines);
    }

    @Then("the system should return the list of vaccines administered to the child")
    public void theSystemShouldReturnTheListOfVaccinesAdministeredToTheChild() {
        assertEquals(administeredVaccines.size(), fetchedAdministeredVaccines.size());

        for (int i = 0; i < administeredVaccines.size(); i++) {
            Vaccine expectedVaccine = administeredVaccines.get(i);
            Vaccine fetchedVaccine = fetchedAdministeredVaccines.get(i);

            assertEquals(expectedVaccine.getName(), fetchedVaccine.getName());
            assertEquals(expectedVaccine.getDateAdministered(), fetchedVaccine.getDateAdministered());
            assertEquals(expectedVaccine.getChild().getId(), fetchedVaccine.getChild().getId());
        }
    }
}
