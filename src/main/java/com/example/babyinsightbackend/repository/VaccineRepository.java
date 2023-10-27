package com.example.babyinsightbackend.repository;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for managing Vaccine entities.
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    /**
     * Find all vaccines administered to a specific child.
     *
     * @param child The child for whom vaccines are to be retrieved.
     * @return A list of vaccines administered to the child.
     */
    List<Vaccine> findByChild(Child child);


    /**
     * Find vaccines by their name.
     *
     * @param name The name of the vaccine.
     * @return A list of vaccines with the specified name.
     */
    List<Vaccine> findByName(String name);


    /**
     * Find vaccines administered after a certain date.
     *
     * @param date The date after which vaccines are administered.
     * @return A list of vaccines administered after the specified date.
     */
    List<Vaccine> findByDateAdministeredAfter(LocalDate date);
}
