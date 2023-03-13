package com.example.springmvc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.entities.RespFormation;

public interface RespFormationRepository extends JpaRepository<RespFormation, Long> {
	
	 public Page<RespFormation> findByNomContains(String mcRF, Pageable pageable);

}
