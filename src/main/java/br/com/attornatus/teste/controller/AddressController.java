package br.com.attornatus.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.teste.model.Address;
import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.repository.AddressRepository;

@RestController
public class AddressController {
	
	@Autowired 
	private AddressRepository addressRepository;
	
	@RequestMapping (value = "/buscarTodosEnderecos", method = RequestMethod.GET)
	public List<Address> getAllPerson() {
		List<Address> listAddress = addressRepository.findAll();
		return listAddress;
	}

}
