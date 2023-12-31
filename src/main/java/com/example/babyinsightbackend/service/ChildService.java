package com.example.babyinsightbackend.service;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.exception.InformationNotFoundException;
import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


/**
 * Service class to manage CRUD operations for Child entities.
 */
@Service
public class ChildService {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;


    /**
     * Constructor to inject the ChildRepository.
     *
     * @param userRepository
     * @param childRepository The repository to interact with the Child table in the database.
     */
    @Autowired
    public ChildService(UserRepository userRepository, ChildRepository childRepository) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
    }

    /**
     * Adds a new child to the database.
     *
     * @param userId
     * @param child  The child entity to be added.
     * @return The added child entity.
     */
    @Transactional
    public Child addChild(Long userId, Child child) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            child.setUser(user);
            user.getChildren().add(child);
            return childRepository.save(child);
        } else {
            throw new InformationNotFoundException("User with id " + userId + " not found");
        }
    }

    /**
     * Fetches all children related to a specific parent.
     *
     * @param userId The parent's user ID.
     * @return A list of child entities associated with the given parent.
     */
    public List<Child> getAllChildrenByParentId(Long userId) {
        return childRepository.findByUserId(userId);
    }

    /**
     * Fetches a child by its ID.
     *
     * @param childId The child's ID.
     * @return An Optional containing the found child or empty if not found.
     */
    public Optional<Child> getChildById(Long childId) {
        return childRepository.findById(childId);
    }

    /**
     * Fetches a child by its ID, throws an exception if the child is not found.
     *
     * @param childId The ID of the child to be found.
     * @return The Child object corresponding to the given ID.
     * @throws EntityNotFoundException If the child with the given ID does not exist.
     */
    public Child findChildById(Long childId) {
        return childRepository.findById(childId).orElseThrow(() -> new EntityNotFoundException("Child not found"));
    }


    /**
     * Fetches a child by its name
     *
     * @param name
     * @return
     */
    public Optional<Child> getChildByName(String name) {
        return childRepository.findByName(name);
    }
    /**
     * Updates the details of a specific child.
     *
     * @param childId      The ID of the child to be updated.
     * @param updatedChild The child entity with updated details.
     * @return The updated child entity.
     */
    public Child updateChild(Long childId, Child updatedChild) {
        if (childRepository.existsById(childId)) {
            updatedChild.setId(childId);
            return childRepository.save(updatedChild);
        } else {
            throw new InformationNotFoundException("Child not found with ID: " + childId);
        }
    }

    /**
     * Deletes a child entity by its ID.
     *
     * @param childId The ID of the child to be deleted.
     *
     */
    public void deleteChild(Long childId) {
        if (childRepository.existsById(childId)) {
            childRepository.deleteById(childId);
        } else {
            throw new InformationExistException("Child not found with ID: " + childId);
        }
    }

    /**
     * Retrieves the date of birth of a child by their ID.
     *
     * @param childId The ID of the child.
     * @return The date of birth of the child or null if not found.
     */
    public LocalDate getChildDateOfBirth(Long childId) {
        Child child = childRepository.findById(childId).orElse(null);
        if (child != null) {
            return child.getDateOfBirth();
        } else {
            return null;
        }
    }

    /**
     * Calculates the age of a child in months based on their date of birth.
     *
     * @param childId The ID of the child.
     * @return The age of the child in months.
     */
    public int calculateChildAgeInMonths(Long childId) {
        LocalDate childDateOfBirth = getChildDateOfBirth(childId);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(childDateOfBirth, currentDate);
        int ageInMonths = period.getYears() * 12 + period.getMonths();
        return ageInMonths;
    }
}
