package com.example.springmvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springmvc.dao.AbsenceRepository;
import com.example.springmvc.dao.FormateurRepository;
import com.example.springmvc.dao.ParticipantRepository;
import com.example.springmvc.dao.PatientRepository;
import com.example.springmvc.dao.PersonneRepository;
import com.example.springmvc.dao.RespFormationRepository;
import com.example.springmvc.dao.formationRepository;
import com.example.springmvc.entities.Absence;
import com.example.springmvc.entities.Formateur;
import com.example.springmvc.entities.Formation;
import com.example.springmvc.entities.RespFormation;
import com.example.springmvc.entities.Participant;
import com.example.springmvc.entities.Patient;
import com.example.springmvc.entities.Personne;

@SpringBootApplication
public class EvaluationFormationApplication implements CommandLineRunner {
	@Autowired
    private PatientRepository patientRepository;
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private RespFormationRepository respFormationRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private formationRepository formationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EvaluationFormationApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		//List<Participant> participants=participantRepository.findParticipants((long) 17);
		//System.out.println(participants);
		//List<Participant> participants=participantRepository.findByFormation((long) 4);
		/*patientRepository.save(new Patient(null, "zouhir",new Date(), false));
		patientRepository.save(new Patient(null, "anass",new Date(), false));
		patientRepository.save(new Patient(null, "youssef",new Date(), true));*/
        //formateurRepository.save(new Formateur("abid","anass",new Date(),"zouhir.gmsolution@gmail.com","math"));
		//participantRepository.save(new Participant("lemrabet","youssef",new Date(),"youssef.lemrabet@gmail.com","commercial"));
		//respFormationRepository.save(new RespFormation("lmerzouki","khalid",new Date(),"khalid.lmerzouki@gmail.com","D5479"));
		
		/*patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});*/
		/*Absence absence=new Absence(new Date(), null, null);
		absenceRepository.save(absence);
		Participant p1=new Participant("tahiri","yassin",new Date(),"tahiri@gmail.com","developer", null);
		Participant p2=new Participant("ajana","yassin",new Date(),"ajana.yassin@gmail.com","ingenieur", null);
		participantRepository.saveAll(Arrays.asList(p1,p2));
		
		absence.getParticipants().addAll(Arrays.asList(p1,p2));
		absenceRepository.save(absence);*/
		
		//absenceRepository absenc1=absenceRepository.save(new Date(),new Formateur("abid","anass",new Date(),"zouhir.gmsolution@gmail.com","math"),new Formation("Qualité",new Date(), new Date(), 20));
		
		//participantRepository.save(p1);
		//participantRepository.save(p2);
		//formationRepository.save(new Formation("Java",new Date(), new Date(), 20));
		//formationRepository.save(new Formation("Communication",new Date(), new Date(), 25));
		//Formation formation1=formationRepository.save(new Formation("Qualité",new Date(), new Date(), 20));
		//participantRepository.save(new Participant("rhaffour","imane",new Date(),"imane.rhaffour@gmail.com","auditrice",formation1));
		
	}

}
