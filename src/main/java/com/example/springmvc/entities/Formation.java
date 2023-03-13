package com.example.springmvc.entities;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity  
public class Formation implements Serializable {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	  @NotNull
	  @Size(min = 3 , max = 20 , message = "Nom invalid")
      private String nomFormation;
      
      @Temporal(TemporalType.DATE)
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      private Date dateDebut;
      
      @Temporal(TemporalType.DATE)
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      private Date dateFin;
      private int nbrPlaces;
      @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "formation")
      @JsonBackReference
      private List<Participant> participants;
      
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "sessionID")
      private Session session;
      
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "formateurID")
      private Formateur formateur;
      
      @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "formation")
      private List<Absence> absences;
      public Formation() {
  		super();
  		// TODO Auto-generated constructor stub
  	}
     public Formation(String nomFormation, Date dateDebut, Date dateFin, int nbrPlaces, Session session,
			Formateur formateur) {
		super();
		this.nomFormation = nomFormation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrPlaces = nbrPlaces;
		this.session = session;
		this.formateur = formateur;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbrPlaces() {
		return nbrPlaces;
	}

	public void setNbrPlaces(int nbrPlaces) {
		this.nbrPlaces = nbrPlaces;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
     
	 
      
	}
     
