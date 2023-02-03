package br.com.efabreu.attornatus.dto;

import br.com.efabreu.attornatus.model.Address;

public class AddressDTO {
	
	private boolean mainAddress;
	
	private String street;
	
	private String number;
	
	private String zipcode;
	
	private String city;

	
	
	public boolean isMainAddress() {
		return mainAddress;
	}



	public void setMainAddress(boolean mainAddress) {
		this.mainAddress = mainAddress;
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



	public Address toObject() {
		return new Address (mainAddress, street, number, zipcode, city);
	}
	
}
