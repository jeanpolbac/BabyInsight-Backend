package com.example.babyinsightbackend.service;

import com.example.babyinsightbackend.exception.InformationNotFoundException;
import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.Vaccine;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


/**
 * Service class to manage CRUD operations on Vaccine entities.
 */
@Service
public class VaccineService {

    Logger logger = Logger.getLogger(VaccineService.class.getName());
    private final VaccineRepository vaccineRepository;
    private final ChildRepository childRepository;
    private final ChildService childService;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository, ChildRepository childRepository, ChildService childService) {
        this.vaccineRepository = vaccineRepository;
        this.childRepository = childRepository;
        this.childService = childService;
    }

    /**
     * Adds a new vaccine to the database.
     *
     * @param vaccine The vaccine entity to be added.
     * @return The added vaccine entity.
     */
    @Transactional
    public Vaccine addVaccine(Vaccine vaccine, Child child) {
        Child foundChild = childRepository.findById(child.getId())
                .orElseThrow(() -> new InformationNotFoundException("Child not found")); // Assuming you have a ChildNotFoundException
        vaccine.setChild(foundChild);
        return vaccineRepository.save(vaccine);
    }
    /**
     * Retrieves all vaccines from the database.
     *
     * @return A list of all vaccine entities.
     */
    public List<Vaccine> getAllVaccines() {
        List<Vaccine> allAvailableVaccines = new ArrayList<>();

        // Birth - 2 months
        allAvailableVaccines.add(new Vaccine(null, "Hepatitis B (HepB) - 1st dose", 0, null));
        allAvailableVaccines.add(new Vaccine(null, "Diphtheria, tetanus, and whooping cough (DTaP) - 1st dose", 2, null));
        allAvailableVaccines.add(new Vaccine(null, "Haemophilus influenzae type b (Hib) - 1st dose", 2, null));
        allAvailableVaccines.add(new Vaccine(null, "Polio (IPV) - 1st dose", 2, null));
        allAvailableVaccines.add(new Vaccine(null, "Pneumococcal (PCV13) - 1st dose", 2, null));
        allAvailableVaccines.add(new Vaccine(null, "Rotavirus (RV) - 1st dose", 2, null));

        // 2 months - 4 months
        allAvailableVaccines.add(new Vaccine(null, "DTaP - 2nd dose", 4, null));
        allAvailableVaccines.add(new Vaccine(null, "Hib - 2nd dose", 4, null));
        allAvailableVaccines.add(new Vaccine(null, "IPV - 2nd dose", 4, null));
        allAvailableVaccines.add(new Vaccine(null, "PCV13 - 2nd dose", 4, null));
        allAvailableVaccines.add(new Vaccine(null, "RV - 2nd dose", 4, null));

        // 4 months - 6 months
        allAvailableVaccines.add(new Vaccine(null, "DTaP - 3rd dose", 6, null));
        allAvailableVaccines.add(new Vaccine(null, "Hib - 3rd dose", 6, null));
        allAvailableVaccines.add(new Vaccine(null, "IPV - 3rd dose", 6, null));
        allAvailableVaccines.add(new Vaccine(null, "PCV13 - 3rd dose", 6, null));
        allAvailableVaccines.add(new Vaccine(null, "RV - 3rd (and last) dose", 6, null));

        // 6 months - 12 months
        allAvailableVaccines.add(new Vaccine(null, "Hepatitis B (HepB) - 2nd and 3rd dose", 12, null));
        allAvailableVaccines.add(new Vaccine(null, "Hib - 4th dose", 12, null));
        allAvailableVaccines.add(new Vaccine(null, "Measles, mumps, and rubella (MMR) - 1st dose", 12, null));
        allAvailableVaccines.add(new Vaccine(null, "Chickenpox (Varicella) - 1st dose", 12, null));
        allAvailableVaccines.add(new Vaccine(null, "Hepatitis A (HepA) - 1st dose", 12, null));

        return allAvailableVaccines;
    }


    /**
     * Retrieves a vaccine by its ID.
     *
     * @param vaccineId The ID of the vaccine to be retrieved.
     * @return An Optional containing the found vaccine or empty if not found.
     */
    public Optional<Vaccine> getVaccineById(Long vaccineId) {
        return vaccineRepository.findById(vaccineId);
    }


    /**
     * Updates the details of a specific vaccine.
     *
     * @param vaccineId      The ID of the vaccine to be updated.
     * @param updatedVaccine The vaccine entity with updated details.
     * @return The updated vaccine entity.
     */
    public Vaccine updateVaccine(Long vaccineId, Vaccine updatedVaccine) {
        updatedVaccine.setVaccineId(vaccineId);
        return vaccineRepository.save(updatedVaccine);
    }


    /**
     * Deletes a vaccine entity by its ID.
     *
     * @param vaccineId The ID of the vaccine to be deleted.
     */
    public void deleteVaccine(Long vaccineId) {
        vaccineRepository.deleteById(vaccineId);
    }


    /**
     * Retrieves the list of vaccines that are remaining for a child.
     *
     * @param userId  The ID of the user (parent) associated with the child.
     * @param childId The ID of the child for whom remaining vaccines are requested.
     * @return A list of remaining vaccine entities for the child.
     */
    public List<Vaccine> getRemainingChildVaccines(Long userId, Long childId) {
        // Retrieve the child's age in months based on their date of birth
        int childAgeInMonths = childService.calculateChildAgeInMonths(childId);

        // Retrieve the list of vaccines required for the child's age
        List<Vaccine> requiredVaccines = getRequiredVaccinesForChildByAge(childAgeInMonths);

        // Retrieve the list of vaccines already administered to the child
        List<Vaccine> administeredVaccines = getChildVaccines(userId, childId);

        // Create a list to store remaining vaccines
        List<Vaccine> remainingVaccines = new ArrayList<>();

        // Compare the required vaccines with the administered vaccines to find remaining ones
        for (Vaccine requiredVaccine : requiredVaccines) {
            boolean vaccineAdministered = false;
            for (Vaccine administeredVaccine : administeredVaccines) {
                if (requiredVaccine.getName().equals(administeredVaccine.getName())) {
                    vaccineAdministered = true;
                    break;
                }
            }
            if (!vaccineAdministered) {
                remainingVaccines.add(requiredVaccine);
            }
        }

        return remainingVaccines;
    }


    /**
     * Retrieves a specific vaccine for a child by ID.
     *
     * @param userId    The ID of the user (parent) associated with the child.
     * @param childId   The ID of the child for whom the vaccine is requested.
     * @param vaccineId The ID of the vaccine to be retrieved.
     * @return An Optional containing the found vaccine for the child or empty if not found.
     */
    public Optional<Vaccine> getChildVaccine(Long userId, Long childId, Long vaccineId) {
        return null;
    }


    /**
     * Retrieves the list of vaccines administered to a child.
     *
     * @param userId  The ID of the user (parent) associated with the child.
     * @param childId The ID of the child for whom administered vaccines are requested.
     * @return A list of vaccine entities administered to the child.
     */
    public List<Vaccine> getChildVaccines(Long userId, Long childId) {
        // Check if the child with the specified ID exists and is associated with the user
        Child child = childRepository.findByIdAndUserId(childId, userId);

        if (child != null) {
            // If the child exists, return the list of administered vaccines for that child
            return child.getAdministeredVaccines();
        }

        // If the child doesn't exist or is not associated with the user, return an empty list or handle the error accordingly.
        return Collections.emptyList();
    }

    /**
     * Retrieves a list of overdue vaccines for a child.
     *
     * @param userId  The ID of the user (parent) associated with the child.
     * @param childId The ID of the child for whom overdue vaccines are requested.
     * @return A list of overdue vaccine entities for the child.
     */
    public List<Vaccine> getOverdueChildVaccines(Long userId, Long childId) {
        // Retrieve the child's age in months based on their date of birth
        int childAgeInMonths = childService.calculateChildAgeInMonths(childId);
        logger.info("Child's age in months: " + childAgeInMonths);

        // Retrieve the list of vaccines required for the child's age
        List<Vaccine> requiredVaccines = getRequiredVaccinesForChildByAge(childAgeInMonths);

        // Retrieve the list of vaccines already administered to the child
        List<Vaccine> administeredVaccines = vaccineRepository.findByChild(childId);

        // Create a list to store overdue vaccines
        List<Vaccine> overdueVaccines = new ArrayList<>();

        // Compare the required vaccines with the administered vaccines to find overdue ones
        for (Vaccine requiredVaccine : requiredVaccines) {
            boolean vaccineAdministered = false;
            for (Vaccine administeredVaccine : administeredVaccines) {
                if (requiredVaccine.getName().equals(administeredVaccine.getName())) {
                    vaccineAdministered = true;
                    break;
                }
            }
            if (!vaccineAdministered) {
                overdueVaccines.add(requiredVaccine);
            }
        }

        return overdueVaccines;
    }

    /**
     * Retrieves a list of required vaccines based on the child's age in months.
     *
     * @param childAgeInMonths The age of the child in months.
     * @return A list of required vaccine entities for the child's age.
     */
    public List<Vaccine> getRequiredVaccinesForChildByAge(int childAgeInMonths) {
        List<Vaccine> vaccines = new ArrayList<>();

        // Determine which vaccines are required based on the child's age
        if (childAgeInMonths < 2) {
            // Birth - 2 months
            vaccines.add(new Vaccine(null, "Hepatitis B (HepB) - 1st dose", 0, null));
            vaccines.add(new Vaccine(null, "Diphtheria, tetanus, and whooping cough (DTaP) - 1st dose", 2, null));
            vaccines.add(new Vaccine(null, "Haemophilus influenzae type b (Hib) - 1st dose", 2, null));
            vaccines.add(new Vaccine(null, "Polio (IPV) - 1st dose", 2, null));
            vaccines.add(new Vaccine(null, "Pneumococcal (PCV13) - 1st dose", 2, null));
            vaccines.add(new Vaccine(null, "Rotavirus (RV) - 1st dose", 2, null));
        } else if (childAgeInMonths < 4) {
            // 2 months - 4 months
            vaccines.add(new Vaccine(null, "DTaP - 2nd dose", 4, null));
            vaccines.add(new Vaccine(null, "Hib - 2nd dose", 4, null));
            vaccines.add(new Vaccine(null, "IPV - 2nd dose", 4, null));
            vaccines.add(new Vaccine(null, "PCV13 - 2nd dose", 4, null));
            vaccines.add(new Vaccine(null, "RV - 2nd dose", 4, null));
        } else if (childAgeInMonths < 6) {
            // 4 months - 6 months
            vaccines.add(new Vaccine(null, "DTaP - 3rd dose", 6, null));
            vaccines.add(new Vaccine(null, "Hib - 3rd dose", 6, null));
            vaccines.add(new Vaccine(null, "IPV - 3rd dose", 6, null));
            vaccines.add(new Vaccine(null, "PCV13 - 3rd dose", 6, null));
            vaccines.add(new Vaccine(null, "RV - 3rd (and last) dose", 6, null));
        } else if (childAgeInMonths < 12) {
            // 6 months - 12 months
            vaccines.add(new Vaccine(null, "Hepatitis B (HepB) - 2nd and 3rd dose", 12, null));
            vaccines.add(new Vaccine(null, "Hib - 4th dose", 12, null));
            vaccines.add(new Vaccine(null, "Measles, mumps, and rubella (MMR) - 1st dose", 12, null));
            vaccines.add(new Vaccine(null, "Chickenpox (Varicella) - 1st dose", 12, null));
            vaccines.add(new Vaccine(null, "Hepatitis A (HepA) - 1st dose", 12, null));
        }


        return vaccines;
    }
}
