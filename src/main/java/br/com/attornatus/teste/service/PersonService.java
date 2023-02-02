package br.com.attornatus.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.repository.AddressRepository;
import br.com.attornatus.teste.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	AddressRepository addressRepository;

	public Optional<Person> getPerson(@RequestParam Long id) {
		return personRepository.findById(id);
	}
	
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public void newPerson(@RequestBody Person person) {
		person.getAddresses().get(0).setMainAddress(true);
		personRepository.save(person);
	}
	
	public void updatePerson(@RequestBody Person personNew, @RequestParam Long id) {
		personNew.setId(id);
		personNew.getAddresses().get(0).setMainAddress(true);
		personRepository.save(personNew);
	}


}
