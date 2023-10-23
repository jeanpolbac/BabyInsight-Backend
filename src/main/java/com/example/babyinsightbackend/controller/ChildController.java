package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}")
public class ChildController {

    static HashMap<String, Object> message = new HashMap<>();

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
    public ResponseEntity<?> getAllChildrenByParent(@PathVariable Long userId) {
        List<Child> children = childService.getAllChildrenByParentId(userId);
        if (children.isEmpty()) {
            message.put("message", "cannot find any children");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "success");
            message.put("data", children);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    /**
     * Retrieve a specific child for a specific parent.
     *
     * @param childId The ID of the child.
     * @return The child details.
     */
    @GetMapping("/children/{childId}")
    public Optional<Child> getChildByParent( @PathVariable Long childId) {
        return childService.getChildById(childId);
    }

    /**
     * Update child details for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param childId The ID of the child.
     * @param child The child details to be updated.
     * @return The updated child.
     */
    @PutMapping("/children/{childId}")
    public Child updateChild(@PathVariable Long userId, @PathVariable Long childId, @RequestBody Child child) {
        return childService.updateChild(userId, childId, child);
    }

    /**
     * Delete a child for a specific parent.
     *
     * @param userId The ID of the parent.
     * @param childId The ID of the child.
     * @return A response indicating success or failure.
     */
    @DeleteMapping("/children/{childId}")
    public ResponseEntity<?> deleteChild(@PathVariable Long userId, @PathVariable Long childId) {
        childService.deleteChild(userId, childId);
        return ResponseEntity.ok().build();
    }

}
