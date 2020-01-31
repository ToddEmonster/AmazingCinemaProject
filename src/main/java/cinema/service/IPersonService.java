package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import cinema.dto.PersonDto;
import cinema.persistence.entity.Nationality;

public interface IPersonService {
	List<PersonDto> getAllPersons();
	Optional<PersonDto> getPersonById(int idPerson);
	Set<PersonDto> getPersonsByNameEndingWithIgnoreCase(String name);
	Set<PersonDto> getPersonsByNameContainingIgnoreCase(String partialName);
	
	@Query("select p from Person p where extract(year from p.birthdate) = ?1")
	Set<PersonDto> getPersonsByBirthdateYear(int year);
	
	PersonDto addPerson(PersonDto person);
	Optional<PersonDto> modifyPerson(PersonDto person);
	Optional<PersonDto> deletePerson(int idPerson);
	
	Set<PersonDto> getPersonsByNationality(Nationality nationality);
	Optional<PersonDto> setNationality(int idPerson, Nationality nationality);
	Optional<PersonDto> getMovieDirector(int idMovie);
	List<PersonDto> getMovieActors(int idMovie);
}
