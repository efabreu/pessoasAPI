package br.com.attornatus.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.attornatus.teste.model.Address;
import br.com.attornatus.teste.model.Person;
import br.com.attornatus.teste.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public Optional<Address> getPerson(@RequestParam Long id) {
		Optional<Address> address = addressRepository.findById(id);
		return address;
	}
	
	public List<Address> getAllPerson() {
		List<Address> listAddress = addressRepository.findAll();
		return listAddress;
	}

	public ResponseEntity<String> newPerson(@RequestBody Address address) {
		addressRepository.save(address);
		return ResponseEntity.status(HttpStatus.OK).body("Endereço salva com sucesso.");
	}
	
//	public void updatePerson(@RequestBody Address address, @RequestParam Long id) {
//		addressRepository.update(address, id);
//	}


}
