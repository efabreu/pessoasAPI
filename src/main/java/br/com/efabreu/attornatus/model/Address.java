package br.com.efabreu.attornatus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
    private Long id;
	
	private boolean mainAddress;
	
	private String street;
	
	private String number;
	
	private String zipcode;
	
	private String city;
	
	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ID_PERSON")
	@JsonIgnore
	private Person person;	
	


	public Address(String street, String number, String zipcode, String city) {
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.city = city;
		this.mainAddress = false;
	}
	
	public Address() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Long getIdPerson() {
		return person.getId();
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(boolean mainAddress) {
		this.mainAddress = mainAddress;
	}
	

}
