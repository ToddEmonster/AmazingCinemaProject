package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Nationality;
import cinema.persistence.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	IPersonService PersonService;
	
	// Methodes Get
	@GetMapping
	@ResponseBody
	public List<Person> allPersons() {
		return PersonService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Person> personById(@RequestParam("id") int idPerson) {
		return PersonService.getPersonById(idPerson);
	}
	
	@GetMapping("/byEndName")
	@ResponseBody
	public Set<Person> personsByNameEnding(@RequestParam("n") String name) {
		return PersonService.getPersonsByNameEndingWithIgnoreCase(name);
	}
	
	@GetMapping("/byNameContaining")
	@ResponseBody
	public Set<Person> personsByNameContaining(@RequestParam("n") String partialName) {
		return PersonService.getPersonsByNameContainingIgnoreCase(partialName);
	}
	
	@GetMapping("/byBirtdateYear")
	@ResponseBody
	public Set<Person> personByBirthdateYear(@RequestParam("y") int year) {
		return PersonService.getPersonsByBirthdateYear(year);
	}
	
	// Methodes Put, Post, Delete
	@PostMapping
	@ResponseBody 
	public Person addPerson(@RequestBody Person person) {
		return PersonService.addPerson(person);
	}
	
	@PutMapping("/modify")
	@ResponseBody 
	public Optional<Person> modifyPerson(@RequestBody Person person) {
		return PersonService.modifyPerson(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody 
	public Optional<Person> deletePerson(@PathVariable("id") int idPerson) {
		return PersonService.deletePerson(idPerson);
	}
	
	@GetMapping("/byNationality")
	@ResponseBody
	public Set<Person> findByNationality(@RequestParam("n") String nationality) {
		return PersonService.getPersonsByNationality(Nationality.valueOf(nationality));
	}
	
	
}
	