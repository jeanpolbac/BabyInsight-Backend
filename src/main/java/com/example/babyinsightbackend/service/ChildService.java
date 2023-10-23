package com.example.babyinsightbackend.service;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.exception.InformationNotFoundException;
import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class to manage CRUD operations for Child entities.
 */
@Service
public class ChildService {

    private final ChildRepository childRepository;

    /**
     * Constructor to inject the ChildRepository.
     *
     * @param childRepository The repository to interact with the Child table in the database.
     */
    @Autowired
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    /**
     * Adds a new child to the database.
     *
     * @param userId
     * @param child  The child entity to be added.
     * @return The added child entity.
     */
    public Child addChild(Long userId, Child child) {
        return childRepository.save(child);
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
     * @param childID
     * @return An Optional containing the found child or empty if not found.
     */
    public Optional<Child> getChildById(Long childId, Long childID) {
        return childRepository.findById(childId);
    }

    /**
     * Updates the details of a specific child.
     *
     * @param childId      The ID of the child to be updated.
     * @param childID
     * @param updatedChild The child entity with updated details.
     * @return The updated child entity.
     */
    public Child updateChild(Long childId, Long childID, Child updatedChild) {
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
     * @param childID
     */
    public void deleteChild(Long childId, Long childID) {
        if (childRepository.existsById(childId)) {
            childRepository.deleteById(childId);
        } else {
            throw new InformationExistException("Child not found with ID: " + childId);
        }
    }

}