package com.example.babyinsightbackend.models;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a child entity with attributes like name, date of birth and associated user.
 */
@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Default constructor.
     */
    public Child() {
    }

    /**
     * Constructs a Child instance with the given parameters.
     *
     * @param name        The child's name.
     * @param dateOfBirth The child's date of birth.
     * @param user        The associated user of the child.
     */
    public Child(String name, LocalDate dateOfBirth, User user) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
    }

    /**
     * Returns the ID of the child.
     *
     * @return the child's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the child.
     *
     * @param id The new ID for the child.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the child.
     *
     * @return the child's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the child.
     *
     * @param name The new name for the child.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the date of birth of the child.
     *
     * @return the child's date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the child.
     *
     * @param dateOfBirth The new date of birth for the child.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the associated user of the child.
     *
     * @return the child's associated user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated user of the child.
     *
     * @param user The new user associated with the child.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", user=" + user +
                '}';
    }
}
