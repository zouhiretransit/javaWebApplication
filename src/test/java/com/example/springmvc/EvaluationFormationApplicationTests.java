package com.example.springmvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springmvc.dao.ParticipantRepository;
import com.example.springmvc.dao.formationRepository;
import com.example.springmvc.entities.Formation;
import com.example.springmvc.entities.Participant;
import com.sun.el.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class EvaluationFormationApplicationTests {
	@MockBean
	private ParticipantRepository participantRepository;
	@Autowired
	private formationRepository formationRepository;
	@Autowired
	private ParticipantRepository participantRepository2;

	@Test
	public void getAllParticipants()
	{
		Formation formation=new Formation("Qualité",new Date(), new Date(), 20 ,null ,null);
		when(participantRepository.findAll()).thenReturn(java.util.stream.Stream.of(new Participant("talbi","hamid",new Date(),"talbi@gmail.com","developer",formation),
				new Participant("messousi","rochdi",new Date(),"rochdi@gmail.com","professeur",formation)).collect(Collectors.toList()));
		assertEquals(2 , participantRepository.findAll().size());
	}

}
