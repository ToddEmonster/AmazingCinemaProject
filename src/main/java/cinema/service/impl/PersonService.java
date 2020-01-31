package cinema.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import cinema.data.Movie;
import cinema.dto.PersonDto;
import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	ModelMapper mapper;
	
	// Methodes get
	@Override
	public List<PersonDto> getAllPersons() {
		return personRepository.findAll().stream()
				.map(pe->mapper.map(pe, PersonDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<PersonDto> getPersonById(int idPerson) {
		return personRepository.findById(idPerson)
							   .map(pe->mapper.map(pe, PersonDto.class));
	}
	
	@Override
	public Set<PersonDto> getPersonsByNameEndingWithIgnoreCase(String name) {
		return personRepository.findByNameEndingWithIgnoreCase(name)
							   .stream()
							   .map(pe->mapper.map(pe, PersonDto.class))
							   .collect(Collectors.toSet());
	}

	@Override
	public Set<PersonDto> getPersonsByNameContainingIgnoreCase(String partialName) {
		return personRepository.findByNameContainingIgnoreCase(partialName)
							   .stream()
							   .map(pe->mapper.map(pe, PersonDto.class))
							   .collect(Collectors.toSet());
	}

	@Override
	public Set<PersonDto> getPersonsByBirthdateYear(int year) {
		return personRepository.findByBirthdateYear(year)
							   .stream()
							   .map(pe->mapper.map(pe, PersonDto.class))
							   .collect(Collectors.toSet());
	}
	
	// Autres methodes
	@Override
	public PersonDto addPerson(PersonDto person) {
		Person personSaved = mapper.map(person, Person.class);
		personRepository.save(personSaved);
		return person;
	}

	@Override
	public Optional<PersonDto> modifyPerson(PersonDto person) {
		Optional<PersonDto> optPerson = personRepository.findById(person.getIdPerson())
										.map(pe->mapper.map(pe, PersonDto.class));
		optPerson.ifPresent(p -> {
			p.setName(person.getName());
			p.setBirthdate(person.getBirthdate());
			p.setBiography(person.getBiography());
			p.setNationality(person.getNationality());
			personRepository.flush();
		});
		return optPerson;
	}

	@Override
	public Optional<PersonDto> deletePerson(int idPerson) {
		Optional<Person> personToDelete = personRepository.findById(idPerson);
		personToDelete.ifPresent(p -> {
			personRepository.delete(p);
			personRepository.flush();
		});
		return personToDelete
					.map(pe->mapper.map(pe, PersonDto.class));
	}

	@Override
	public Set<PersonDto> getPersonsByNationality(Nationality nationality) {
		return personRepository.findByNationality(nationality)
				.stream()
				.map(pe->mapper.map(pe, PersonDto.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Optional<PersonDto> getMovieDirector(int idMovie) {
		return movieRepository.findById(idMovie)
				.map(me-> {
					Person d = me.getDirector();
					return Objects.isNull(d) ? null : mapper.map(d, PersonDto.class);
				});
	}
		
	@Override
	public Optional<List<PersonDto>> getMovieActors(int idMovie) {
		return movieRepository.findById(idMovie)
				.map(me-> {
					return me.getActors().stream()
					.map(a-> mapper.map(a, PersonDto.class))
					.collect(Collectors.toList());
				});
	}	
		
	@Override
	public Optional<PersonDto> setNationality(int idPerson, Nationality nationality) {
		Optional<PersonDto> optPerson = personRepository.findById(idPerson)
				 						.map(pe->mapper.map(pe, PersonDto.class));
		optPerson.ifPresent(p-> 
				p.getNationality().add(nationality)
				);
		return optPerson;
	}
	
}
