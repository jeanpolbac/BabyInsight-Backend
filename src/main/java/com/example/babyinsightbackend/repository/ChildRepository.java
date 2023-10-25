package com.example.babyinsightbackend.repository;

import com.example.babyinsightbackend.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findByUserId(Long userId);

    Optional<Child> findByName(String name);

    Child findByIdAndUserId(Long childId, Long userId);
}
