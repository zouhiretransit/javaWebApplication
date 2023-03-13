package com.example.springmvc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.springmvc.entities.Formation;
import com.example.springmvc.entities.Participant;

public interface formationRepository extends JpaRepository<Formation, Long> {
	
	public Page<Formation> findByNomFormationContains(String mcF, Pageable pageable);
	

}
