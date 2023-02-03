package br.com.attornatus.teste.service;

import java.util.ArrayList;
import java.util.List;

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

	public List<Address> getAddressById(@RequestParam Long idPerson) {
		List<Address> addresses = addressRepository.getAddressesById(idPerson);
		return addresses;
	}
	
	public List<Address> getAllAddress() {
		List<Address> listAddress = addressRepository.findAll();
		return listAddress;
	}

	public ResponseEntity<String> newAddress(@RequestBody List<Address> addresses, @RequestParam Long idPerson) {
		Person person = new Person();
		person.setId(idPerson);
		addresses.forEach(address->address.setPerson(person));
		addressRepository.saveAll(addresses);
		return ResponseEntity.status(HttpStatus.OK).body("Endereço salvo com sucesso.");
	}
	
	public List<Address> checkIfMainAddress(Long idPerson, Address address) {
		Address oldMainAddress = addressRepository.getMainAddressById(idPerson, true);
		List<Address> listAddress = new ArrayList<Address>();
		if (oldMainAddress != null && address.isMainAddress()) {
			oldMainAddress.setMainAddress(false);
			listAddress.add(oldMainAddress);
			listAddress.add(address);
			return listAddress; //caso novo endereço seja o principal e já tenha um endereço principal cadastrado
		}else if(!addressRepository.existsById(idPerson) && !address.isMainAddress()) {
			address.setMainAddress(true);
			listAddress.add(address);
			return listAddress; //caso novo endereço nao esteja marcado como o principal e não exista nenhum endereco cadastrado
		}
		listAddress.add(address);
		return listAddress;
	}
	
	public Address getMainAddressById (Long idPerson) {
		return addressRepository.getMainAddressById(idPerson, true);
	}
	
	
	
//	public void updatePerson(@RequestBody Address address, @RequestParam Long id) {
//		addressRepository.update(address, id);
//	}


}
