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

import java.util.HashMap;
import java.util.Map;
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
//    @PostMapping(path = "/register/")
//    public ResponseEntity<?> createUser(@RequestBody User userObject) {
//        try {
//            User newUser = userService.createUser(userObject);
//            logger.info("User successfully created: " + newUser.getEmailAddress());
//            return new ResponseEntity<>("User successfully created.", HttpStatus.CREATED);
//        } catch (InformationExistException e) {
//            logger.warning("Email already exists: " + userObject.getEmailAddress());
//            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
//        }
//    }

    @PostMapping(path = "/register/")
    public ResponseEntity<?> createUser(@RequestBody User userObject) {
        Map<String, String> response = new HashMap<>();
        try {
            User newUser = userService.createUser(userObject);
            logger.info("User successfully created: " + newUser.getEmailAddress());
            response.put("message", "User successfully created.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (InformationExistException e) {
            logger.warning("Email already exists: " + userObject.getEmailAddress());
            response.put("message", "Email already exists.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
    /**
     * Endpoint for user login.
     *
     * @param loginRequest The login request containing the user's email address and password.
     * @return ResponseEntity with a JWT token and HTTP status 200 if authentication is successful,
     * or a message indicating authentication failure with HTTP status 401 if authentication fails.
     */
//    @PostMapping(path = "/login/")
//    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
//        try {
//            Optional<String> jwtToken = userService.loginUser(loginRequest);
//            if (jwtToken.isPresent()) {
//                logger.info("Authentication successful for user " + loginRequest.getEmailAddress());
//                return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
//            } else {
//                logger.warning("Authentication failed for user " + loginRequest.getEmailAddress());
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
//            }
//        } catch (InformationNotFoundException e) {
//            logger.warning("User not found: " + loginRequest.getEmailAddress());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponse("User not found"));
//        } catch (InvalidPasswordException e) {
//            logger.warning("Invalid password for user " + loginRequest.getEmailAddress());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid password"));
//        } catch (Exception e) {
//            logger.severe("An unexpected error occurred: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse("An unexpected error occurred"));
//        }
//    }
//    @PostMapping(path = "/login/")
//    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Optional<String> jwtToken = userService.loginUser(loginRequest);
//            if (jwtToken.isPresent()) {
//                logger.info("Authentication successful for user " + loginRequest.getEmailAddress());
//                User loggedInUser = userService.getCurrentLoggedInUser();
//                LoginResponse loginResponse = new LoginResponse(jwtToken.get(), loggedInUser);
//                response.put("token", jwtToken.get());
//                response.put("message", "Authentication successful.");
//                return new ResponseEntity<>(loginResponse, HttpStatus.OK);
//            } else {
//                logger.warning("Authentication failed for user " + loginRequest.getEmailAddress());
//                response.put("message", "Authentication failed.");
//                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (InformationNotFoundException e) {
//            logger.warning("User not found: " + loginRequest.getEmailAddress());
//            response.put("message", "User not found.");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        } catch (InvalidPasswordException e) {
//            logger.warning("Invalid password for user " + loginRequest.getEmailAddress());
//            response.put("message", "Invalid password.");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        } catch (Exception e) {
//            logger.severe("An unexpected error occurred: " + e.getMessage());
//            response.put("message", "An unexpected error occurred.");
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping(path = "/login/")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<String> jwtToken = userService.loginUser(loginRequest);
            if (jwtToken.isPresent()) {
                logger.info("Authentication successful for user " + loginRequest.getEmailAddress());

                // Fetch the current logged-in user
                User loggedInUser = userService.getCurrentLoggedInUser();

                // Construct the LoginResponse with the JWT token and user object
                LoginResponse loginResponse = new LoginResponse(jwtToken.get(), loggedInUser);

                response.put("token", jwtToken.get());
                response.put("message", "Authentication successful.");

                return new ResponseEntity<>(loginResponse, HttpStatus.OK);
            } else {
                logger.warning("Authentication failed for user " + loginRequest.getEmailAddress());
                response.put("message", "Authentication failed.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (InformationNotFoundException e) {
            logger.warning("User not found: " + loginRequest.getEmailAddress());
            response.put("message", "User not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (InvalidPasswordException e) {
            logger.warning("Invalid password for user " + loginRequest.getEmailAddress());
            response.put("message", "Invalid password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.severe("An unexpected error occurred: " + e.getMessage());
            response.put("message", "An unexpected error occurred.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
