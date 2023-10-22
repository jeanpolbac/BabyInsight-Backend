package com.example.babyinsightbackend.service;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.models.request.LoginRequest;
import com.example.babyinsightbackend.repository.UserRepository;

import com.example.babyinsightbackend.security.JWTUtils;
import com.example.babyinsightbackend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Service class for handling business logic related to User operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    private AuthenticationManager authenticationManager;

    /**
     * Constructs a new UserService object.
     *
     * @param userRepository        The repository used to interact with User data in the database.
     * @param passwordEncoder
     * @param jwtUtils
     * @param authenticationManager
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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
            userObject.setPassword(passwordEncoder.encode((userObject.getPassword())));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user email address " + userObject.getEmailAddress() + " already exists");
        }
    }


    /**
     * Authenticates a user based on the provided login credentials and generates a JWT token upon successful authentication.
     *
     * @param loginRequest The login request containing the user's email address and password.
     * @return An Optional containing the JWT token if authentication is successful, or an empty Optional if authentication fails.
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    /**
     * Fetches a User based on the provided email address.
     *
     * @param emailAddress The email address associated with the desired user.
     * @return User instance associated with the given email address, or null if no such user exists.
     */
    public User findByUserEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}
