package com.example.babyinsightbackend.security;

import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *  UserDetailsService interface for authentication and authorization.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    /**
     * Setter method for the UserService dependency.
     *
     * @param userService The user service instance.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a user record based on the email address. Converts this to a UserDetails object for security handling.
     *
     * @param emailAddress The email address of the user.
     * @return A UserDetails instance built from the user's data.
     * @throws UsernameNotFoundException if the user with the provided email address is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userService.findByUserEmailAddress(emailAddress);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + emailAddress + " not found");
        }

        return new MyUserDetails(user);
    }
}
