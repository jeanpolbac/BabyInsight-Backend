package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}")
public class ChildController {


    @Autowired
    private ChildService childService;

    /**
     * Create a new child for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param child The child details to be added.
     * @return The added child.
     */
    @PostMapping("/children")
    public Child createChild(@PathVariable Long userId, @RequestBody Child child) {
        return childService.addChild(userId, child);
    }

    /**
     * Retrieve all children for a specific parent.
     *
     * @param userId The ID of the parent.
     * @return A list of children.
     */
    @GetMapping("/children")
    public List<Child> getAllChildrenByParent(@PathVariable Long userId) {
        return childService.getAllChildrenByParentId(userId);
    }

    /**
     * Retrieve a specific child for a specific parent.
     *
     * @param userId  The ID of the parent.
     * @param childID The ID of the child.
     * @return The child details.
     */
    @GetMapping("/children/{childID}")
    public Optional<Child> getChildByParent(@PathVariable Long userId, @PathVariable Long childID) {
        return childService.getChildById(userId, childID);
    }

    /**
     * Update child details for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param childID The ID of the child.
     * @param child The child details to be updated.
     * @return The updated child.
     */
    @PutMapping("/children/{childID}")
    public Child updateChild(@PathVariable Long userId, @PathVariable Long childID, @RequestBody Child child) {
        return childService.updateChild(userId, childID, child);
    }

    /**
     * Delete a child for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param childID The ID of the child.
     * @return A response indicating success or failure.
     */
    @DeleteMapping("/children/{childID}")
    public ResponseEntity<?> deleteChild(@PathVariable Long userId, @PathVariable Long childID) {
        childService.deleteChild(userId, childID);
        return ResponseEntity.ok().build();
    }

}
