package br.com.efabreu.attornatus.dto;

import br.com.efabreu.attornatus.model.Address;

public class AddressDTO {
	
	private String street;
	
	private String number;
	
	private String zipcode;
	
	private String city;

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



	public Address toObject() {
		return new Address (street, number, zipcode, city);
	}
	
}
