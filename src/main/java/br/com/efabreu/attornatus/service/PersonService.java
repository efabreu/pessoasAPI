package br.com.efabreu.attornatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.efabreu.attornatus.dto.PersonDTO;
import br.com.efabreu.attornatus.exceptions.PersonNotFoundExcepion;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.repository.AddressRepository;
import br.com.efabreu.attornatus.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	AddressRepository addressRepository;

	public Person getPerson(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundExcepion());
	}

	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public Person create(PersonDTO personDTO) {
		Person person = personDTO.toObject();
		personRepository.save(person);
		return person;
	}
	
	public boolean update(PersonDTO personDTONew, Long id) {
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
