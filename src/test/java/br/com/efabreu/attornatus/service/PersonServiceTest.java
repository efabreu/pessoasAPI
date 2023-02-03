package br.com.efabreu.attornatus.service;


import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bellabiango.harrypottermanager.entity.MovieCharacter;
import br.com.efabreu.attornatus.dto.PersonDTO;
import br.com.efabreu.attornatus.exceptions.PersonNotFoundExcepion;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.repository.PersonRepository;

@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
	PersonService personService;
	
	@MockBean
	PersonRepository personRepository;
	
	@Test
	public void creatingSucess() throws PersonNotFoundExcepion{
		
		PersonDTO personDTO = Instancio.of(PersonDTO.class)
				.create();
		personService.newPerson(personDTO);
		
		Mockito.when(personRepository.save(Mockito.any(Person.class))).
		thenAnswer(i -> {
			Person personToReturn =	i.getArgument(0);
			personToReturn.setId(1L);
			return personToReturn;
		});
		
		Person returnedPerson = personService.newPerson(personDTO);
		
		Assertions.assertEquals(personDTO.getName(), returnedPerson.getName());
		Assertions.assertEquals(personDTO.getBirthday(), returnedPerson.getBirthday());
		
	}

}
