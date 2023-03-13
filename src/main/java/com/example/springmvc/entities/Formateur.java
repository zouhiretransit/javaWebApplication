package com.example.springmvc.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	
	private String matier;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "formateur")
    private List<Formation> formations;

	public Formateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Formateur( String nom, String prenom, Date dateNaissance, String email, String matier) {
		super( nom, prenom, dateNaissance, email);
		this.matier = matier;
	}



	public String getMatier() {
		return matier;
	}

	public void setMatier(String matier) {
		this.matier = matier;
	}



	public List<Formation> getFormations() {
		return formations;
	}



	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	


}
