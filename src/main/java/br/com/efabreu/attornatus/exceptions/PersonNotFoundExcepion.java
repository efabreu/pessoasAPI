package br.com.efabreu.attornatus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExcepion extends Exception {

	public PersonNotFoundExcepion() {
		super(String.format("Pessoa não encontrada"));
	}
	
	public PersonNotFoundExcepion(Long id) {
		super(String.format("Pessoa não encontrada id: ", id));
	}

}


