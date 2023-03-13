package com.example.springmvc.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@DiscriminatorValue("participant")
public class Participant extends Personne {
	
	private String fonction;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formationID")
	@JsonBackReference
	private Formation formation;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "participants")
	@JsonBackReference
	private List<Absence> absences=new ArrayList<Absence>();
	
	public Participant(String nom, String prenom, Date dateNaissance, String email, String fonction,
			Formation formation) {
		super(nom, prenom, dateNaissance, email);
		this.fonction = fonction;
		this.formation = formation;
	}


	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}


	/*public Participant(String nom, String prenom, Date dateNaissance, String email, String fonction) {
		super(nom, prenom, dateNaissance, email);
		this.fonction = fonction;
	}*/


	public String getFonction() {
		return fonction;
	}


	public Formation getFormation() {
		return formation;
	}


	public void setFormation(Formation formation) {
		this.formation = formation;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}


	public List<Absence> getAbsences() {
		return absences;
	}


	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	

}
