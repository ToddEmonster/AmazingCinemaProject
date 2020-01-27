package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Person;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	// Methodes get
	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	@Override
	public Optional<Person> getPersonById(int idPerson) {
		return personRepository.findById(idPerson);
	}
	
	@Override
	public Set<Person> getPersonsByNameEndingWithIgnoreCase(String name) {
		return personRepository.findByNameEndingWithIgnoreCase(name);
	}

	@Override
	public Set<Person> getPersonsByNameContainingIgnoreCase(String partialName) {
		return personRepository.findByNameContainingIgnoreCase(partialName);
	}

	@Override
	public Set<Person> getPersonsByBirthdateYear(int year) {
		return personRepository.findByBirthdateYear(year);
	}
	
	// Autres methodes
	@Override
	public Person addPerson(Person person) {
		Person personSaved = personRepository.save(person);
		return personSaved;
	}

	@Override
	public Optional<Person> modifyPerson(Person person) {
		var optPerson = personRepository.findById(person.getIdPerson());
		optPerson.ifPresent(p -> {
			p.setName(person.getName());
			p.setBirthdate(person.getBirthdate());
			personRepository.flush();
		});
		return optPerson;
	}

	@Override
	public Optional<Person> deletePerson(int idPerson) {
		var personToDelete = personRepository.findById(idPerson);
		personToDelete.ifPresent(p -> {
			personRepository.delete(p);
			personRepository.flush();
		});
		return personToDelete;
	}
	
}
