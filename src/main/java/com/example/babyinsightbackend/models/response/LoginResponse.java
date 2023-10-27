/**
 * Represents a response object for user login.
 */
package com.example.babyinsightbackend.models.response;

import com.example.babyinsightbackend.models.User;

public class LoginResponse {

    /**
     * The JSON Web Token (JWT) for authentication.
     */
    private String jwt;

    /**
     * The user object associated with the login response.
     */
    public User user;

    /**
     * Constructs a new LoginResponse with the provided JWT and user object.
     *
     * @param jwt  The JSON Web Token (JWT) for authentication.
     * @param user The user object associated with the login response.
     */
    public LoginResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    /**
     * Get the JSON Web Token (JWT) for authentication.
     *
     * @return The JWT string.
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Set the JSON Web Token (JWT) for authentication.
     *
     * @param jwt The JWT string to set.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Get the user object associated with the login response.
     *
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user object associated with the login response.
     *
     * @param user The user object to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
