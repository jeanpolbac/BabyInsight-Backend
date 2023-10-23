package com.example.babyinsightbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


/**
 * Represents a user(Parent) in the Baby Insight application.
 * This entity contains basic user information like username, email, and password.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * The email address associated with the user. Must be unique across all users.
     */
    @Column(length = 100, unique = true, nullable = false)
    private String emailAddress;

    /**
     * The hashed password for the user.
     */
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Child> children;
    /**
     * Default constructor required for JPA.
     */
    public User() {
    }

    /**
     * Constructor to initialize a user with specified attributes.
     * @param id The unique identifier for the user.
     * @param emailAddress The email address of the user.
     * @param password The hashed password for the user.
     */
    public User(Long id, String userName, String emailAddress, String password) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The user's new ID.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Gets the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address for the user.
     *
     * @param emailAddress The user's new email address.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the hashed password of the user.
     *
     * @return The user's hashed password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the hashed password for the user.
     *
     * @param password The user's new hashed password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the user object,
     * which includes the user's ID, username, and email address.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

