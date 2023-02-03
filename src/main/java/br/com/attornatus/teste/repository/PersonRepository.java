package br.com.attornatus.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.teste.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Long id);
	
}
