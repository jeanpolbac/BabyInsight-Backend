package com.example.babyinsightbackend.service;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service class for handling business logic related to User operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService object.
     *
     * @param userRepository The repository used to interact with User data in the database.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user in the database. Throws an exception if the user's email already exists.
     *
     * @param userObject The user to be created.
     * @return The created user.
     * @throws InformationExistException If the user's email address already exists in the database.
     */
    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // ToDo - encrypt password
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user email address " + userObject.getEmailAddress() + " already exists");
        }
    }
}
