package com.example.springmvc.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvc.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
	public Page<Session>  findByNomSessionContains(String mcSession,Pageable pageable);
	

}
