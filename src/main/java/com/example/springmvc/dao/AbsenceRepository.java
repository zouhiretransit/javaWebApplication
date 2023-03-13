package com.example.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springmvc.entities.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long>  {
    
	
}
