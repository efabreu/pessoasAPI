package br.com.efabreu.attornatus.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.efabreu.attornatus.dto.PersonDTO;
import br.com.efabreu.attornatus.exceptions.PersonNotFoundExcepion;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoa")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping (value = "/{id}")
	@ApiOperation(value = "Buscar pessoa.")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Pessoa encontrada.", response = Person.class),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Pessoa não encontrada.", response = PersonNotFoundExcepion.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public ResponseEntity<?> buscarPessoaPorId(@RequestParam Long id) {
		Person person = personService.getPerson(id).orElse(null);
		return Objects.isNull(person)? ResponseEntity.notFound().build() : ResponseEntity.ok(person);
	}
	
	@GetMapping (value = "/")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Lista de pessoas cadastrada.", response = Person[].class),
		@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message="Não foram encontrado pessoas.", response = Object.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	@ApiOperation(value = "Buscar todas as pessoas cadastradas.")
	public List<Person> buscarTodasPessoas() {
		return personService.getAllPerson();
	}
	
	@PostMapping(value = "/")
	@ApiOperation(value = "Criar pessoa.")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Pessoa cadastrada.", response = Long.class),
		@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campos nome e data de nascimento invalidos.", response = Person.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public ResponseEntity<String> criarPessoa(@RequestBody PersonDTO personDTO) {
		
		Person person = personDTO.toObject();
		personService.newPerson(person);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa salva com sucesso.");
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualizar pessoa.")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Pessoa atualizada.", response = Person.class),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Pessoa não encontrada.", response = PersonNotFoundExcepion.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public ResponseEntity<Object> atualizarPessoa(@RequestBody Person person, @PathVariable Long id) {
		if(!personService.updatePerson(person, id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		};
		
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Remover pessoa.")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message="Pessoa removida.", response=Object.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		personService.remove(id);
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	

}
