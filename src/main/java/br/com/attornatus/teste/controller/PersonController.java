package br.com.attornatus.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.repository.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping (value = "/buscarPessoa", method = RequestMethod.GET)
	public Optional<Person> getPerson(@RequestParam Long id) {
		Optional<Person> person = personRepository.findById(id);
		return person;
	}
	
	@RequestMapping (value = "/buscarTodasPessoas", method = RequestMethod.GET)
	public List<Person> getAllPerson() {
		List<Person> listPerson = personRepository.findAll();
		return listPerson;
	}
	
	@PostMapping(value = "/criarPessoa")
//	@RequestMapping (value = "/criarPessoa", method = RequestMethod.POST)
	public ResponseEntity<String> newPerson(@RequestBody Person person) {
		personRepository.save(person);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa salva com sucesso.");
	}
	
	

}
