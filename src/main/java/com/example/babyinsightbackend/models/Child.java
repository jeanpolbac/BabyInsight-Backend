package com.example.babyinsightbackend.models;

import javax.persistence.*;
import java.time.LocalDate;

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

}
