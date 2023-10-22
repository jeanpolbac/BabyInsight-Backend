package com.example.babyinsightbackend.repository;

import com.example.babyinsightbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity.
 * Provides methods to interact with the database for user-related operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if a user with the specified email address exists in the database.
     *
     * @param userEmailAddress The email address to check.
     * @return true if a user with the given email address exists, false otherwise.
     */
    boolean existsByEmailAddress(String userEmailAddress);

    /**
     * Retrieves a User entity by its email address.
     *
     * @param emailAddress The email address of the user to be retrieved.
     * @return the User entity associated with the given email address, or null if not found.
     */
    User findUserByEmailAddress(String emailAddress);
}
