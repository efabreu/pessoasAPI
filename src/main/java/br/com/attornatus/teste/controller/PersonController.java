package br.com.attornatus.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.repository.AddressRepository;
import br.com.attornatus.teste.repository.PersonRepository;
import br.com.attornatus.teste.service.AddressService;
import br.com.attornatus.teste.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping (value = "/buscarPessoa", method = RequestMethod.GET)
	public Optional<Person> buscarPessoa(@RequestParam Long id) {
		return personService.getPerson(id);
	}
	
	@RequestMapping (value = "/buscarTodasPessoas", method = RequestMethod.GET)
	public List<Person> buscarTodasPessoas() {
		return personService.getAllPerson();
	}
	
	@PostMapping(value = "/criarPessoa")
//	@RequestMapping (value = "/criarPessoa", method = RequestMethod.POST)
	public ResponseEntity<String> criarPessoa(@RequestBody Person person) {
		personService.newPerson(person);
		
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa salva com sucesso.");
	}
	
	@PostMapping(value = "/atualizarPessoa")
	public ResponseEntity<String> atualizarPessoa(@RequestBody Person person, @RequestParam Long id) {
		personService.updatePerson(person, id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa atualizada com sucesso.");
	}
	

}
