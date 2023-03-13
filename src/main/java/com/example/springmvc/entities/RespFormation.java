package com.example.springmvc.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("responsableFormation")
public class RespFormation extends Personne {
      
      private String matricule;

	public RespFormation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespFormation(String nom, String prenom, Date dateNaissance, String email, String matricule) {
		super(nom, prenom, dateNaissance, email);
		this.matricule = matricule;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
      
}
