package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * Controller class for managing Child entities.
 *
 */
@RestController
@RequestMapping("/api/users/{userId}")
public class ChildController {

    HashMap<String, Object> message = new HashMap<>();

    @Autowired
    private ChildService childService;

    /**
     * Create a new child for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param child The child details to be added.
     * @return The added child.
     */
    @PostMapping("/children/")
    public Child createChild(@PathVariable Long userId, @RequestBody Child child) {
        return childService.addChild(userId, child);
    }

    /**
     * Retrieve all children for a specific parent.
     *
     * @param userId The ID of the parent.
     * @return A list of children.
     *  @throws InformationExistException if the User entity is not found.
     *  @throws Exception for any other unexpected errors.
     */
    @GetMapping("/children/")
    public ResponseEntity<?> getAllChildrenByParent(@PathVariable Long userId) {
        try {
            List<Child> children = childService.getAllChildrenByParentId(userId);
            if (children.isEmpty()) {
                return new ResponseEntity<>("No children found for the given user ID: " + userId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(children, HttpStatus.OK);
        } catch (InformationExistException e) {
            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve a specific child for a specific parent.
     *
     * @param childId The ID of the child.
     * @return The child details.
     */
    @GetMapping("/children/{childId}/")
    public Optional<Child> getChildByParent( @PathVariable Long childId) {
        return childService.getChildById(childId);
    }

    /**
     * Update child details for a specific parent.
     *
     * @param childId The ID of the child.
     * @param child The child details to be updated.
     * @return The updated child.
     */
    @PutMapping("/children/{childId}/")
    public Child updateChild(@PathVariable Long childId, @RequestBody Child child) {
        return childService.updateChild(childId, child);
    }

    /**
     * Delete a child for a specific parent.
     *
     * @param childId The ID of the child.
     * @return A response indicating success or failure.
     */
    @DeleteMapping("/children/{childId}/")
    public ResponseEntity<?> deleteChild(@PathVariable Long childId) {
        try {
            childService.deleteChild(childId);
            return ResponseEntity.ok().build();
        } catch (InformationExistException e) {
            return new ResponseEntity<>("Child not found with ID: " + childId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
