package br.com.attornatus.teste.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping (value = "/buscarPessoa", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPessoa(@RequestParam Long id) {
		Person person = personService.getPerson(id).orElse(null);
		return Objects.isNull(person)? ResponseEntity.notFound().build() : ResponseEntity.ok(person);
	}
	
	@RequestMapping (value = "/buscarTodasPessoas", method = RequestMethod.GET)
	public List<Person> buscarTodasPessoas() {
		return personService.getAllPerson();
	}
	
	@PostMapping(value = "/criarPessoa")
	public ResponseEntity<String> criarPessoa(@RequestBody Person person) {
		personService.newPerson(person);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa salva com sucesso.");
	}
	
	@PutMapping(value = "/atualizarPessoa")
	public ResponseEntity<String> atualizarPessoa(@RequestBody Person person, @RequestParam Long id) {
		if(!personService.updatePerson(person, id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
		};
		
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa atualizada com sucesso.");
	}
	

}
