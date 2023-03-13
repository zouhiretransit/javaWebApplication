package com.example.springmvc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	
	public Page<Formateur> findByNomContains(String mcF,Pageable pageable);

}
