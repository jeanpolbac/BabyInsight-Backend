package com.example.babyinsightbackend.seed;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.repository.ChildRepository;
import com.example.babyinsightbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserDataLoader(UserRepository userRepository, ChildRepository childRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setEmailAddress("coolparent@email.com");
            user1.setPassword(passwordEncoder.encode("password123"));

            Child child1 = new Child();
            child1.setName("Baby");
            child1.setDateOfBirth(LocalDate.now());
            child1.setUser(user1);

            user1.getChildren().add(child1);

            userRepository.save(user1);
            childRepository.save(child1);
        }
    }
}