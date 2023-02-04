package br.com.efabreu.attornatus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.efabreu.attornatus.dto.AddressDTO;
import br.com.efabreu.attornatus.model.Address;
import br.com.efabreu.attornatus.model.Person;
import br.com.efabreu.attornatus.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAddressById(Long idPerson) {
		List<Address> addresses = addressRepository.getAddressesById(idPerson);
		return addresses;
	}
	
	public List<Address> getAllAddress() {
		List<Address> listAddress = addressRepository.findAll();
		return listAddress;
	}

	public ResponseEntity<Object> newAddress(List<Address> addresses, Long idPerson) {
		Person person = new Person();
		person.setId(idPerson);
		addresses.forEach(address->address.setPerson(person));
		addressRepository.saveAll(addresses);
		return ResponseEntity.status(HttpStatus.OK).body(addresses);
	}
	
	public List<Address> checkIfMainAddress(Long idPerson, AddressDTO addressDTO, Boolean mainAddress) {
		Address address = addressDTO.toObject();
		address.setMainAddress(mainAddress);
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

}
