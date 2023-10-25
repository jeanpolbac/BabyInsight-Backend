package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.exception.InformationNotFoundException;
import com.example.babyinsightbackend.exception.InvalidPasswordException;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.models.request.LoginRequest;
import com.example.babyinsightbackend.models.response.LoginResponse;
import com.example.babyinsightbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;


/**
 * Controller for user-related operations, such as user registration.
 */
@RestController
@RequestMapping("/auth/users")
public class UserController {

    /**
     * Service for handling user-related operations.
     */
    @Autowired
    private UserService userService;


    /**
     * Logger for logging events and errors.
     */
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
        try {
            User newUser = userService.createUser(userObject);
            logger.info("User successfully created: " + newUser.getEmailAddress());
            return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
        } catch (InformationExistException e) {
            logger.warning("Email already exists: " + userObject.getEmailAddress());
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
    }


    /**
     * Endpoint for user login.
     *
     * @param loginRequest The login request containing the user's email address and password.
     * @return ResponseEntity with a JWT token and HTTP status 200 if authentication is successful,
     * or a message indicating authentication failure with HTTP status 401 if authentication fails.
     */
    @PostMapping(path = "/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        try {
            Optional<String> jwtToken = userService.loginUser(loginRequest);
            if (jwtToken.isPresent()) {
                logger.info("Authentication successful for user " + loginRequest.getEmailAddress());
                return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
            } else {
                logger.warning("Authentication failed for user " + loginRequest.getEmailAddress());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
            }
        } catch (InformationNotFoundException e) {
            logger.warning("User not found: " + loginRequest.getEmailAddress());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponse("User not found"));
        } catch (InvalidPasswordException e) {
            logger.warning("Invalid password for user " + loginRequest.getEmailAddress());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid password"));
        } catch (Exception e) {
            logger.severe("An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse("An unexpected error occurred"));
        }
    }
}
