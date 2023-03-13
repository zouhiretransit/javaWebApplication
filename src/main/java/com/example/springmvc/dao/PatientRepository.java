package com.example.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
