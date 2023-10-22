package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


/**
 * Controller for user-related operations, such as user registration.
 */
@RestController
@RequestMapping("/auth/users")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(UserController.class.getName());

    /**
     * Endpoint for user registration.
     *
     * @param userObject The user object to be registered.
     * @return ResponseEntity with a success message and HTTP status 201 if registration is successful,
     * or a message indicating that the email already exists with HTTP status 200 if the email is already registered.
     */
    @PostMapping(path = "/register/")
    public ResponseEntity<?> createUser(@RequestBody User userObject) {
        User newUser = userService.createUser(userObject);
        if (newUser != null) {
            logger.info("User successfully created: " + newUser.getUserName());
            return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email already exists.", HttpStatus.OK);
        }
    }
}
