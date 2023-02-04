package br.com.efabreu.attornatus.service;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.efabreu.attornatus.dto.PersonDTO;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.repository.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PersonRepository personRepository;
	
	@Test
	public void creatingSucess(){
		
		PersonDTO personDTO = Instancio.of(PersonDTO.class)
				.create();
		
		Mockito.when(personRepository.save(Mockito.any(Person.class))).
		thenAnswer(i -> {
			Person personToReturn =	i.getArgument(0);
			personToReturn.setId(1L);
			return personToReturn;
		});
		
		Person returnedPerson = personService.create(personDTO);
		
		Assertions.assertEquals(personDTO.getName(), returnedPerson.getName());
		Assertions.assertEquals(personDTO.getBirthday(), returnedPerson.getBirthday());
		
	}
	
	

}
