package com.example.springmvc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springmvc.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	
	public Page<Participant> findByNomContains(String mc, Pageable pageable);
	
	@Query("select p from Participant p INNER JOIN p.formation pf where pf.id = :idFormation")
	public List<Participant> findByFormation_Id(@Param("idFormation") Long idFormation);
	
	@Query("select p from Participant p join p.absences pa where pa.id= :idAbsence")
	public List<Participant> findParticipants(@Param("idAbsence") Long i);
	
	
	
	

}
