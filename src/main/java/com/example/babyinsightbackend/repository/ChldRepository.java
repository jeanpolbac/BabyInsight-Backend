package com.example.babyinsightbackend.repository;

import com.example.babyinsightbackend.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChldRepository extends JpaRepository<Child, Long>
}
