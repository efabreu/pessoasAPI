package br.com.attornatus.teste.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.teste.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Long id);
	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE PERSON SET NAME = :#{#person.name}, BIRTHDAY = :#{#person.birthday}"
//			+ " where ID = :id", nativeQuery = true)
//	void update(@Param("person") Person person, @Param("id") Long id);
	
}
