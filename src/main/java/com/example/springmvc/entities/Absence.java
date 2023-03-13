package com.example.springmvc.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date dateAbsence;
	@ManyToOne
	@JoinColumn(name = "Code_formateur")
	private Formateur formateur;
	@ManyToOne
	@JoinColumn(name = "id_formation")
	private Formation formation;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "absence_participants",
	          joinColumns = {@JoinColumn(name = "absence_id")},
	          inverseJoinColumns = {@JoinColumn(name = "participant_id")}
	)
	@JsonBackReference
	private List<Participant> participants=new ArrayList<Participant>();
	
	public Absence() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Absence(Date dateAbsence, Formateur formateur, Formation formation) {
		super();
		this.dateAbsence = dateAbsence;
		this.formateur = formateur;
		this.formation = formation;
	}

	/*public Absence(Date dateAbsence, Formateur formateur, Formation formation, Set<Participant> participants) {
		super();
		this.dateAbsence = dateAbsence;
		this.formateur = formateur;
		this.formation = formation;
		this.participants = participants;
	}*/


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
	
	
	
 
}