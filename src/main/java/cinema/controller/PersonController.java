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

import cinema.dto.PersonDto;
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
	public List<PersonDto> allPersons() {
		return PersonService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<PersonDto> personById(@RequestParam("id") int idPerson) {
		return PersonService.getPersonById(idPerson);
	}
	
	@GetMapping("/byEndName")
	@ResponseBody
	public Set<PersonDto> personsByNameEnding(@RequestParam("n") String name) {
		return PersonService.getPersonsByNameEndingWithIgnoreCase(name);
	}
	
	@GetMapping("/byNameContaining")
	@ResponseBody
	public Set<PersonDto> personsByNameContaining(@RequestParam("n") String partialName) {
		return PersonService.getPersonsByNameContainingIgnoreCase(partialName);
	}
	
	@GetMapping("/byBirtdateYear")
	@ResponseBody
	public Set<PersonDto> personByBirthdateYear(@RequestParam("y") int year) {
		return PersonService.getPersonsByBirthdateYear(year);
	}
	
	// Methodes Put, Post, Delete
	@PostMapping
	@ResponseBody 
	public PersonDto addPerson(@RequestBody PersonDto person) {
		return PersonService.addPerson(person);
	}
	
	@PutMapping("/modify")
	@ResponseBody 
	public Optional<PersonDto> modifyPerson(@RequestBody PersonDto person) {
		return PersonService.modifyPerson(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody 
	public Optional<PersonDto> deletePerson(@PathVariable("id") int idPerson) {
		return PersonService.deletePerson(idPerson);
	}
	
	@GetMapping("/byNationality")
	@ResponseBody
	public Set<PersonDto> findByNationality(@RequestParam("n") String nationality) {
		return PersonService.getPersonsByNationality(Nationality.valueOf(nationality));
	}
	
	@PutMapping("/setNationality")
	@ResponseBody
	public Optional<PersonDto> setNationality(@RequestParam("id") int idPerson,
										   @RequestParam("n") String nationality){
		return PersonService.setNationality(idPerson, Nationality.valueOf(nationality));
	}
}
	