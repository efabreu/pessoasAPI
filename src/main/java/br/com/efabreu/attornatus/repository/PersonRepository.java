package br.com.efabreu.attornatus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.efabreu.attornatus.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Long id);
	
}
