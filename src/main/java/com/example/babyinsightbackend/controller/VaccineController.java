package com.example.babyinsightbackend.controller;

import com.example.babyinsightbackend.models.Child;
import com.example.babyinsightbackend.models.Vaccine;
import com.example.babyinsightbackend.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    // Endpoint to add a new vaccine
    @PostMapping
    public Vaccine addVaccine(@RequestBody Vaccine vaccine) {
        Child child = vaccine.getChild();
        return vaccineService.addVaccine(vaccine, child);
    }


    // Endpoint to retrieve all vaccines
    @GetMapping("/api/vaccines")
    public List<Vaccine> getAllVaccines() {
        return vaccineService.getAllVaccines();
    }

    // Endpoint to retrieve a vaccine by ID
    @GetMapping("/{vaccineId}")
    public Optional<Vaccine> getVaccineById(@PathVariable Long vaccineId) {
        return vaccineService.getVaccineById(vaccineId);
    }

    // Endpoint to update a vaccine
    @PutMapping("/{vaccineId}")
    public Vaccine updateVaccine(@PathVariable Long vaccineId, @RequestBody Vaccine updatedVaccine) {
        return vaccineService.updateVaccine(vaccineId, updatedVaccine);
    }

    // Endpoint to delete a vaccine
    @DeleteMapping("/{vaccineId}")
    public void deleteVaccine(@PathVariable Long vaccineId) {
        vaccineService.deleteVaccine(vaccineId);
    }

    // Endpoint to retrieve vaccines for a specific child of a parent
    @GetMapping("/users/{userId}/children/{childId}/vaccines/administered/")
    public List<Vaccine> getChildVaccines(@PathVariable Long userId, @PathVariable Long childId) {
        return vaccineService.getChildVaccines(userId, childId);
    }

    // Endpoint to retrieve remaining vaccines for a specific child of a parent
    @GetMapping("/users/{userId}/children/{childId}/vaccines/remaining/")
    public List<Vaccine> getRemainingChildVaccines(@PathVariable Long userId, @PathVariable Long childId) {
        return vaccineService.getRemainingChildVaccines(userId, childId);
    }

    // Endpoint to retrieve a specific vaccine for a child of a parent
    @GetMapping("/users/{userId}/children/{childId}/vaccines/{vaccineId}")
    public ResponseEntity<Vaccine> getChildVaccine(@PathVariable Long userId, @PathVariable Long childId, @PathVariable Long vaccineId) {
        Optional<Vaccine> vaccine = vaccineService.getChildVaccine(userId, childId, vaccineId);
        return vaccine.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to retrieve overdue vaccines for a specific child of a parent
    @GetMapping("/users/{userId}/children/{childId}/vaccines/overdue/")
    public List<Vaccine> getOverdueChildVaccines(@PathVariable Long userId, @PathVariable Long childId) {
        return vaccineService.getOverdueChildVaccines(userId, childId);
    }
}