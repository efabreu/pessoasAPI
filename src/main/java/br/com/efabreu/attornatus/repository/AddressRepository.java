package br.com.efabreu.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.efabreu.attornatus.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query(value = "SELECT * FROM ADDRESS WHERE ID_PERSON = :idPerson"
			+ " AND MAIN_ADDRESS = :isMainAddress",
			nativeQuery = true)
	Address getMainAddressById(@Param("idPerson")Long idPerson,@Param("isMainAddress") Boolean isMainAddress);
	
	@Query(value = "SELECT * FROM ADDRESS WHERE ID_PERSON = :idPerson",
			nativeQuery = true)
	List<Address> getAddressesById(@Param("idPerson")Long idPerson);
	
}
