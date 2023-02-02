package br.com.attornatus.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.teste.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	

}
