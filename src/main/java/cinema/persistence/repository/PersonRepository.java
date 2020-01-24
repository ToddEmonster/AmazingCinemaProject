package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	Set<Person> findByName(String name);
	Set<Person> findByNameContaining(String name);
	Set<Person> findByNameIgnoreCaseContaining(String name);
	
	@Query("select p from Person p where extract(year from birthDate) = ?1")
	Set<Person> findByBirthDateYear(int year);
}
