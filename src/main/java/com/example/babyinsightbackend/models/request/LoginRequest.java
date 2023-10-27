/**
 * Represents a request object for user login.
 */
package com.example.babyinsightbackend.models.request;

public class LoginRequest {

    /**
     * The email address of the user attempting to log in.
     */
    private String emailAddress;

    /**
     * The password of the user attempting to log in.
     */
    private String password;

    /**
     * Get the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
}
