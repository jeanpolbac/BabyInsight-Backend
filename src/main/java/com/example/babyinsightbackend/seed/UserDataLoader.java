package com.example.babyinsightbackend.seed;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.models.Vaccine;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A DataLoader class to seed initial user and child data along with administered vaccines.
 */

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final PasswordEncoder passwordEncoder;



    /**
     * Constructor to inject dependencies.
     *
     * @param userRepository     The UserRepository to manage user data.
     * @param childRepository    The ChildRepository to manage child data.
     * @param passwordEncoder    The PasswordEncoder to encode user passwords.
     */
    @Autowired
    public UserDataLoader(UserRepository userRepository, ChildRepository childRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Loads initial user and child data along with administered vaccines.
     *
     * @param args Command-line arguments.
     */
    @Override
    public void run(String... args) {
        loadUserData();
    }


    /**
     * Loads initial user and child data along with administered vaccines.
     */
    private void loadUserData() {
        if (userRepository.count() == 0) {

            // Create a new user
            User user1 = new User();
            user1.setEmailAddress("coolparent@email.com");
            user1.setPassword(passwordEncoder.encode("password123"));

            // Create a new child associated with the user
            Child child1 = new Child();
            child1.setName("Baby Yoda");
            child1.setDateOfBirth(LocalDate.now());
            child1.setUser(user1);

            // Add the child to the user's list of children
            user1.getChildren().add(child1);

            // Create administered vaccines for the child
            Vaccine administeredVaccine1 = new Vaccine();
            administeredVaccine1.setName("Hepatitis B - Administered 1");
            administeredVaccine1.setDateAdministered(LocalDate.now());
            administeredVaccine1.setChild(child1);

            Vaccine administeredVaccine2 = new Vaccine();
            administeredVaccine2.setName("Hepatitis B - Administered 2");
            administeredVaccine2.setDateAdministered(LocalDate.now());
            administeredVaccine2.setChild(child1);

            // Create a list of administered vaccines for the child
            List<Vaccine> administeredVaccines = new ArrayList<>();
            administeredVaccines.add(administeredVaccine1);
            administeredVaccines.add(administeredVaccine2);

            // Set the list of administered vaccines for the child
            child1.setAdministeredVaccines(administeredVaccines);

            // Save the user and child data to the repositories
            userRepository.save(user1);
            childRepository.save(child1);
        }
    }
}