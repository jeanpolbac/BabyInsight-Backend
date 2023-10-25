package com.example.babyinsightbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccineId;



    @Column
    private String name;

    @Column
    private int recommendedAge;

    @Column
    private LocalDate dateAdministered;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;



    public Vaccine() {
    }

    public Vaccine(Long vaccineId, String name, int recommendedAge, LocalDate dateAdministered) {
        this.vaccineId = vaccineId;
        this.name = name;
        this.recommendedAge = recommendedAge;
        this.dateAdministered = dateAdministered;
    }


    public Long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(String recommendedAge) {
        this.recommendedAge = Integer.parseInt(recommendedAge);
    }

    public LocalDate getDateAdministered() {
        return dateAdministered;
    }

    public void setDateAdministered(LocalDate dateAdministered) {
        this.dateAdministered = dateAdministered;
    }

    public Child getChild() {
        return child;
    }

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
