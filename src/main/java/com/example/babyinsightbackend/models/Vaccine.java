/**
 * Represents a vaccine entity with details such as vaccine ID, name, recommended age, and date administered.
 */
package com.example.babyinsightbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    /**
     * The unique identifier for the vaccine.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccineId;

    /**
     * The name of the vaccine.
     */
    @Column
    private String name;

    /**
     * The recommended age for administering the vaccine.
     */
    @Column
    private int recommendedAge;

    /**
     * The date when the vaccine was administered.
     */
    @Column
    private LocalDate dateAdministered;

    /**
     * The child associated with the vaccine.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    /**
     * Constructs a new Vaccine with the provided details.
     *
     * @param vaccineId       The unique identifier for the vaccine.
     * @param name            The name of the vaccine.
     * @param recommendedAge  The recommended age for administering the vaccine.
     * @param dateAdministered The date when the vaccine was administered.
     */
    public Vaccine(Long vaccineId, String name, int recommendedAge, LocalDate dateAdministered) {
        this.vaccineId = vaccineId;
        this.name = name;
        this.recommendedAge = recommendedAge;
        this.dateAdministered = dateAdministered;
    }

    /**
     * Get the unique identifier for the vaccine.
     *
     * @return The vaccine ID.
     */
    public Long getVaccineId() {
        return vaccineId;
    }

    /**
     * Set the unique identifier for the vaccine.
     *
     * @param vaccineId The vaccine ID to set.
     */
    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    /**
     * Get the name of the vaccine.
     *
     * @return The vaccine name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the vaccine.
     *
     * @param name The vaccine name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the recommended age for administering the vaccine.
     *
     * @return The recommended age.
     */
    public int getRecommendedAge() {
        return recommendedAge;
    }

    /**
     * Set the recommended age for administering the vaccine.
     *
     * @param recommendedAge The recommended age to set.
     */
    public void setRecommendedAge(String recommendedAge) {
        this.recommendedAge = Integer.parseInt(recommendedAge);
    }

    /**
     * Get the date when the vaccine was administered.
     *
     * @return The date administered.
     */
    public LocalDate getDateAdministered() {
        return dateAdministered;
    }

    /**
     * Set the date when the vaccine was administered.
     *
     * @param dateAdministered The date administered to set.
     */
    public void setDateAdministered(LocalDate dateAdministered) {
        this.dateAdministered = dateAdministered;
    }

    /**
     * Get the child associated with the vaccine.
     *
     * @return The child object.
     */
    public Child getChild() {
        return child;
    }

    /**
     * Set the child associated with the vaccine.
     *
     * @param child The child object to set.
     */
    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "vaccineId=" + vaccineId +
                ", child=" + child +
                ", name='" + name + '\'' +
                ", recommendedAge='" + recommendedAge + '\'' +
                ", dateAdministered=" + dateAdministered +
                '}';
    }
}
