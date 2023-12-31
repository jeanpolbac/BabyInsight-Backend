package com.example.babyinsightbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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


    /**
     * The associated user of the child.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The list of vaccines required for the child.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

    /**
     * The list of vaccines already administered to the child.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Vaccine> administeredVaccines;

    /**
     * Default constructor.
     */
    public Child() {
    }

    /**
     * Constructs a Child instance with the given parameters.
     *
     * @param name                 The child's name.
     * @param dateOfBirth          The child's date of birth.
     * @param user                 The associated user of the child.
     * @param vaccines             The list of vaccines required for the child.
     * @param administeredVaccines The list of vaccines already administered to the child.
     */

    public Child(String name, LocalDate dateOfBirth, User user, List<Vaccine> vaccines, List<Vaccine> administeredVaccines) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
        this.vaccines = vaccines;
        this.administeredVaccines = administeredVaccines;
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


    /**
     * Returns the list of administered vaccines for the child.
     *
     * @return the list of administered vaccines.
     */
    public List<Vaccine> getAdministeredVaccines() {
        return administeredVaccines;
    }

    /**
     * Sets the list of administered vaccines for the child.
     *
     * @param administeredVaccines The new list of administered vaccines for the child.
     */
    public void setAdministeredVaccines(List<Vaccine> administeredVaccines) {
        this.administeredVaccines = administeredVaccines;
    }

    /**
     * Returns the list of vaccines required for the child.
     *
     * @return the list of vaccines.
     */
    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    /**
     * Sets the list of vaccines required for the child.
     *
     * @param vaccines The new list of vaccines required for the child.
     */
    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }


    /**
     * Returns the string representation of the Child object.
     *
     * @return A string containing the child's ID, name, date of birth, and associated user.
     */
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
