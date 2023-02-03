package br.com.attornatus.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.teste.model.Address;
import br.com.attornatus.teste.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired 
	private AddressService addressService;
	
	@GetMapping (value = "/buscarEnderecoPor")
	public List<Address> buscarTodosEnderecos(@RequestParam Long idPerson) {
		return addressService.getAddressById(idPerson);
	}
	
	@GetMapping (value = "/buscarEnderecoPrincipal")
	public Address buscarEnderecoPrincipal(@RequestParam Long idPerson) {
		return addressService.getMainAddressById(idPerson);
	}
	
	@PostMapping (value = "/salvarEndereco")
	public ResponseEntity<String> salvarEndereco(@RequestBody Address address, @RequestParam Long idPerson) {
		List<Address> listAddresses = addressService.checkIfMainAddress(idPerson, address);
		return addressService.newAddress(listAddresses, idPerson);
	}

}
