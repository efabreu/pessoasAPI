package br.com.efabreu.attornatus.dto;

import br.com.efabreu.attornatus.model.Person;

public class PersonDTO {
	
	private String name;
	
	private String birthday;

	public PersonDTO(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public Person toObject() {
		return new Person (name, birthday);
	}

}
