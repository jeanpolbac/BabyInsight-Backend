package com.example.babyinsightbackend.security;

import com.example.babyinsightbackend.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;


/**
 * Custom UserDetails implementation to bridge the application's User model with Spring Security's requirements.
 */
public class MyUserDetails implements UserDetails {

    private final User user;


    /**
     * Constructs a new MyUserDetails object with the specified User entity.
     *
     * @param user The User entity.
     */
    public MyUserDetails(User user) {
        this.user = user;
    }


    /**
     * Retrieves the authorities (roles/permissions) granted to the user.
     *
     * @return A collection of granted authorities for the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }


    /**
     * Retrieves the user's password.
     *
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    /**
     * Retrieves the username (in this case, email address) of the user.
     *
     * @return The email address of the user.
     */
    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }


    /**
     * Checks if the user's account has not expired.
     *
     * @return True if the account is still active, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * Checks if the user's account is not locked.
     *
     * @return True if the account is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Checks if the user's credentials (password) have not expired.
     *
     * @return True if the credentials are valid, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * Checks if the user's account is enabled.
     *
     * @return True if the account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * Retrieves the associated User entity.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }
}
