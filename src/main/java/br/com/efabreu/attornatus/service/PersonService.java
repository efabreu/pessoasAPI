package br.com.efabreu.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.efabreu.attornatus.dto.PersonDTO;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.repository.AddressRepository;
import br.com.efabreu.attornatus.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	AddressRepository addressRepository;

	public Optional<Person> getPerson(Long id) {
		return personRepository.findById(id);
	}
	
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public void newPerson(PersonDTO personDTO) {
		Person person = personDTO.toObject();
		personRepository.save(person);
	}
	
	public boolean updatePerson(PersonDTO personDTONew, Long id) {
		Person personNew = personDTONew.toObject();
		if (!personRepository.existsById(id)) {
			return false;
		};
		personNew.setId(id);
		personRepository.save(personNew);
		return true;
	}

	public void remove(Long id) {
		personRepository.deleteById(id);		
	}


}
