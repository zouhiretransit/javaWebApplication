package com.example.springmvc.web;


import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springmvc.dao.AbsenceRepository;
import com.example.springmvc.dao.FormateurRepository;
import com.example.springmvc.dao.ParticipantRepository;
import com.example.springmvc.dao.PatientRepository;
import com.example.springmvc.dao.RespFormationRepository;
import com.example.springmvc.dao.SessionRepository;
import com.example.springmvc.dao.formationRepository;
import com.example.springmvc.entities.Absence;
import com.example.springmvc.entities.Formateur;
import com.example.springmvc.entities.Formation;
import com.example.springmvc.entities.Participant;
import com.example.springmvc.entities.Patient;
import com.example.springmvc.entities.RespFormation;
import com.example.springmvc.entities.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javassist.expr.NewArray;
import lombok.RequiredArgsConstructor;

@Controller
public class EvaluationFormationController {
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private RespFormationRepository respFormationRepository;
	@Autowired
	private formationRepository formationRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	
	
	
	@GetMapping(path = "/index")
	public String index()
	{
		
		return "index";
	}
	
	@GetMapping(path = "/evFormation")
	public String evFormation()
	{
		return "EvFormation";
	}
	
	
	/* --------------- Gestion Des Absences ------------- */
	
	@GetMapping(path = "/listeAbsences")
	public String listeAbsences(Model model)
	{
		List<Absence> listeAbsences=absenceRepository.findAll();
		model.addAttribute("listeAbsences",listeAbsences);
		return "listeAbsences";
		
	}
	
	@GetMapping(path = "/chercherAbsence")
	public String chercherAbsence(Model model, LocalDate dateAbsence)
	{
		return "listeAbsences";
	}
	
	@GetMapping(path = "/showAbsence")
	public String showAbsence(Model model, Long id)
	{
		List<Participant> participantsAbsence=participantRepository.findParticipants(id);
		Absence absence=absenceRepository.findById(id).orElse(null);
		model.addAttribute("participantsAbsence",participantsAbsence);
		model.addAttribute("absence",absence);
		return "showAbsence";
	}
	@GetMapping(path = "/formAbsence")
	public String formabsences(Model model)
	{
		model.addAttribute("absence", new Absence());
		model.addAttribute("formateurs", formateurRepository.findAll());
		model.addAttribute("formations", formationRepository.findAll());
		return "formAbsence";
		
	}
	/*@RequestMapping(value = "/getParticipants", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Participant> getParticipants(@RequestParam("idFormation") Long idFormation)
	{
		List<Participant> participants=participantRepository.findByFormation_Id(idFormation);
		return participants;
		
	}*/
      //@PostMapping(path = "/ajouterAbsence",consumes = "application/x-www-form-urlencoded", produces = "application/json")
	@PostMapping(path = "/ajouterAbsence")
	public String ajouterAbsences(Model model, @Valid Absence absence,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "formAbsence";
		}
		else {
			Absence abs=absenceRepository.save(absence);
		    List<Participant> participants=participantRepository.findByFormation_Id(abs.getFormation().getId());
			model.addAttribute("formateurs", formateurRepository.findAll());
			model.addAttribute("formations", formationRepository.findAll());
			model.addAttribute("listParticipants",participants);
			return "ajouterAbsences";
		}
		
		
	}
	//@PostMapping(path = "/ajouterParticipantAbsence")
	@RequestMapping(value = "/ajouterParticipantAbsence",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Participant ajouterParticipantAbsence(@RequestParam("idParticipant") Long idParticipant,@RequestParam("idAbsence") Long idAbsence)
	{
		Participant participant=participantRepository.findById(idParticipant).get();
		Absence absence=absenceRepository.findById(idAbsence).get();
		absence.getParticipants().add(participant);
		absenceRepository.save(absence);
		return participant;
		
	}
	
	@RequestMapping(value = "/supprimerParticipantAbsence", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Participant supprimerParticipantAbsence(@RequestParam("idParticipant") Long idParticipant,@RequestParam("idAbsence") Long idAbsence )
	{
		Participant participant=participantRepository.findById(idParticipant).get();
		Absence absence=absenceRepository.findById(idAbsence).get();
		absence.getParticipants().remove(participant);
		absenceRepository.save(absence);
		return participant;
	}
	
	
	/* --------------- Gestion Des Formations ------------- */
	@GetMapping(path = "/formations")
	public String listFormations(Model model,
			                    @RequestParam(name = "nomFormation", defaultValue = "") String mcF,
			                    @RequestParam(name = "page",defaultValue = "0") int page,
			                    @RequestParam(name = "size", defaultValue = "5") int size)
	{
		Page<Formation> listFormations=formationRepository.findByNomFormationContains(mcF, PageRequest.of(page, size));
		model.addAttribute("listF",listFormations.getContent());
		model.addAttribute("pages", new int[listFormations.getTotalPages()]);
		model.addAttribute("motCleF", mcF);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		return "formations";
	}
	@GetMapping(path = "/formFormation")
	public String formFormations(Model model)
	{
		model.addAttribute("formation", new Formation());
		model.addAttribute("sessions", sessionRepository.findAll());
		model.addAttribute("formateurs", formateurRepository.findAll());
		return "formFormation";
	}
	@PostMapping(path = "/ajouterFormation")
	public String ajouterFormations(Model model,@Valid Formation formation,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			model.addAttribute("sessions", sessionRepository.findAll());
			model.addAttribute("formateurs", formateurRepository.findAll());
    		return "formFormation";
		}
    	else {
			
    	formationRepository.save(formation);
    	return "formFormation";
    	}
	}
	
	@GetMapping(path = "/supprimerFormation")
	public String supprimerFormation(Long id, String nomFormation, int page, int size)
	{
		formationRepository.deleteById(id);
		return "redirect:/formations?page="+page+"&size="+size+"&nomFormation="+nomFormation;
	}
	
	@GetMapping(path = "/modifierFormation")
	public String modifierFormations(Model model, Long id)
	{
		Formation f=formationRepository.findById(id).get();
		model.addAttribute("sessions", sessionRepository.findAll());
		model.addAttribute("formateurs", formateurRepository.findAll());
		model.addAttribute("formation", f);
		return "formFormation";
		
	}
	
	/* --------------- Gestion Des Participants ------------- */
	
	@GetMapping(path = "/participants")
	public String listParticipant(Model model,
			                       @RequestParam(name = "page",defaultValue = "0") int page,
			                       @RequestParam(name = "size", defaultValue = "5") int size,
			                       @RequestParam(name = "nom", defaultValue = "") String mc)
	{
		Page<Participant> listParticipants=participantRepository.findByNomContains(mc, PageRequest.of(page, size));
		model.addAttribute("listP", listParticipants.getContent());
		model.addAttribute("pages",new int[listParticipants.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", mc);
		return "participants";
	}
	@GetMapping(path = "/supprimerParticipant")
	public String supprimerPart(Long id, String nom, int page, int size)
	{
		participantRepository.deleteById(id);
		return "redirect:/participants?page="+page+"&size="+size+"&nom="+nom;
	}
    @GetMapping(path = "/formParticipant")
    public String formParticipant(Model model)
    {
    	//List<Formation> listeFormations=formationRepository.findAll();
    	model.addAttribute("participant", new Participant());
    	model.addAttribute("formations", formationRepository.findAll());
    	return "formParticipant";
    }
    @PostMapping(path = "/ajouterParticipant")
    public String ajouterParticipants(Model model,@Valid Participant participant, BindingResult bindingResult)
    {
    	if (bindingResult.hasErrors()) {
    		return "formParticipant";
		}
    	else {
			
    	participantRepository.save(participant);
    	model.addAttribute("formations", formationRepository.findAll());
    	return "formParticipant";
    	}
    }
	@GetMapping(path = "/modifierParticipant")
	public String modifierParticipants(Model model , Long id)
	{
	   Participant p=participantRepository.findById(id).get();
	   model.addAttribute("formations", formationRepository.findAll());
	   model.addAttribute("participant", p);
	   return "formParticipant";
	}
	
	
	/* --------------- Gestion Des Formateurs ------------- */
	
	@GetMapping(path = "/formateurs")
	public String listFormateur(Model model,
			                     @RequestParam(name = "page", defaultValue = "0") int page,
			                     @RequestParam(name = "size", defaultValue = "10") int size,
			                     @RequestParam(name = "nom", defaultValue = "") String mcF)
	{
		Page<Formateur> pageFormateurs=formateurRepository.findByNomContains(mcF,PageRequest.of(page, size));
		model.addAttribute("listF",pageFormateurs.getContent());
		model.addAttribute("pages", new int[pageFormateurs.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("size", size);
		model.addAttribute("motCleF",mcF);
		return "formateurs";
	}

	@GetMapping(path = "/supprimerFormateur")
	public String supprimerFormateur(Long id, String nom, int page, int size)
	{
		formateurRepository.deleteById(id);
		return "redirect:/formateurs?page="+page+"&size="+size+"&nom="+nom;
	}
	@GetMapping(path = "/formFormateur")
	public String formFormateur(Model model)
	{
		model.addAttribute("formateur", new Formateur());
		return "formFormateur";
	}
	@PostMapping(path = "/ajouterFormateur")
	public String ajouterFormateur(@Valid Formateur formateurA, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "formFormateur";
		}
		else {
		formateurRepository.save(formateurA);
		return "formFormateur";
		}
	}
	@GetMapping(path = "/modifierFormateur")
	public String modifierformateurs(Model model,Long id)
	{
		Formateur f=formateurRepository.findById(id).get();
		model.addAttribute("formateur", f);
		return "formFormateur";
		
	}
	
	/*-----------------Gestion Les responsables Des Formations ----------------*/
	
	@GetMapping(path = "/responsablesFormations")
	public String listRespFormation(Model model,
			                        @RequestParam(name = "page", defaultValue = "0") int page,
			                        @RequestParam(name = "size", defaultValue = "5") int size, 
			                        @RequestParam(name = "nomRF", defaultValue = "") String nomRF)
	{
		Page<RespFormation> pageRespFormations=respFormationRepository.findByNomContains(nomRF, PageRequest.of(page, size));
		model.addAttribute("listRF",pageRespFormations.getContent());
	    model.addAttribute("pagesRF",new int[pageRespFormations.getTotalPages()]);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("size", size);
	    model.addAttribute("motCleRF", nomRF);
		return "responsableFormation";
	}
	
	@GetMapping(path = "/supprimerRespFormation")
	public String supprimerResponsableFormation(Long id, int page, int size, String nomRF)
	{
		respFormationRepository.deleteById(id);
		return "redirect:/responsablesFormations?page="+page+"&size="+size+"&nomRF="+nomRF;
	}
	
	@GetMapping(path = "/formResponsableFormation")
	public String formResponsableFormations(Model model)
	{
		model.addAttribute("respFormation", new RespFormation());
		return "formResponsableFormation";
	}
	
	@PostMapping(path = "/ajouterResponsableFormation")
	public String ajouterResponsableFormation(@Valid RespFormation respFormation,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "formResponsableFormation";
		}
		else {
		respFormationRepository.save(respFormation);
		return "formResponsableFormation";
		}
	}
	
	@GetMapping(path = "/modifierResponsableFormation")
	public String modifierResponsableFormation(Model model, Long id)
	{
		RespFormation respFormation=respFormationRepository.findById(id).get();
		model.addAttribute("respFormation",respFormation);
		return "formResponsableFormation";
	}
	
	/*-----------------Gestion Des Session ----------------*/
    
	@GetMapping(path = "/session")
	public String session()
	{
		return "session";
	}
	@GetMapping(path = "/listSession")
	public String listSession(Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "7") int size,
			@RequestParam (name="nomSessionCle", defaultValue = "") String nomSessionCle)
	{
		Page<Session> listSessions=sessionRepository.findByNomSessionContains(nomSessionCle,PageRequest.of(page, size));
		model.addAttribute("motCleS", nomSessionCle);
		model.addAttribute("listSessions", listSessions.getContent());
		model.addAttribute("pagesS",new int[listSessions.getTotalPages()]);
	    model.addAttribute("currentPageS", page);
	    model.addAttribute("size", size);
		return "listSession";
	}
	@GetMapping(path = "/formSession")
	public String formSessions(Model model)
	{
		model.addAttribute("session", new Session());
		return "formSession";
	}
	@PostMapping(path = "/ajouterSession")
	public String ajouterSessions(Model model,@Valid Session session,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "formSession";
		}
		else {
			sessionRepository.save(session);
		    return "formSession";
		}
	}
	@GetMapping(path = "/supprimerSession")
	public String supprimerSessions(Long id,int page, int size,@RequestParam (name="nomSessionCle", defaultValue = "") String nomSessionCle)
	{
		sessionRepository.deleteById(id);
		return "redirect:/listSession?page="+page+"&size="+size+"&nomSessionCle="+nomSessionCle;
	}
	
	@GetMapping(path = "/modifierSession")
	public String modifierSessions(Long id, Model model)
	{
		Session session=sessionRepository.findById(id).get();
		model.addAttribute("session", session);
		return "formSession";
	}

}