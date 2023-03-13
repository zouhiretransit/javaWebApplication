package com.example.springmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.entities.Formateur;
import com.example.springmvc.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
