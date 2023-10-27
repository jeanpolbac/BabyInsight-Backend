/**
 * Repository interface for managing Child entities in the database.
 */
package com.example.babyinsightbackend.repository;

import com.example.babyinsightbackend.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    /**
     * Find all children associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of child entities associated with the user.
     */
    List<Child> findByUserId(Long userId);

    /**
     * Find a child by their name.
     *
     * @param name The name of the child to search for.
     * @return An optional child entity with the specified name.
     */
    Optional<Child> findByName(String name);

    /**
     * Find a child by their ID and associated user's ID.
     *
     * @param childId The unique identifier of the child.
     * @param userId  The unique identifier of the user associated with the child.
     * @return The child entity with the specified ID and associated user's ID.
     */
    Child findByIdAndUserId(Long childId, Long userId);
}
