package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import cinema.persistence.entity.Person;

public interface IPersonService {
	List<Person> getAllPersons();
	Optional<Person> getPersonById(int idPerson);
	Set<Person> getPersonsByNameEndingWithIgnoreCase(String name);
	Set<Person> getPersonsByNameContainingIgnoreCase(String partialName);
	
	@Query("select p from Person p where extract(year from p.birthdate) = ?1")
	Set<Person> getPersonsByBirthdateYear(int year);
	
	Person addPerson(Person person);
	Optional<Person> modifyPerson(Person person);
	Optional<Person> deletePerson(int idPerson);
	
}
