package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
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

import cinema.data.Movie;
import cinema.dto.PersonDto;
import cinema.persistence.entity.Nationality;
import cinema.service.IMovieService;
import cinema.service.IPersonService;
import cinema.service.impl.MovieService;

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
	
	@GetMapping("/byBirthdateYear")
	@ResponseBody
	public Set<PersonDto> personByBirthdateYear(@RequestParam("y") int year) {
		return PersonService.getPersonsByBirthdateYear(year);
	}
	
	@GetMapping("/director/{idM}")
	@ResponseBody
	public Optional<PersonDto> getMovieDirector(@PathVariable("idM") int idMovie) {
		return PersonService.getMovieDirector(idMovie);
	}
	
	@GetMapping("/actors/{idM}")
	@ResponseBody
	public Optional<List<PersonDto>> getMovieActors(@PathVariable("idM") int idMovie) {
		return PersonService.getMovieActors(idMovie);		
	}
	
	@GetMapping("/byNationality")
	@ResponseBody
	public Set<PersonDto> personsByNationality(@RequestParam("n") String nationality) {
		return PersonService.getPersonsByNationality(Nationality.valueOf(nationality));
	}	
	
	
	
	
	// Methodes Put, Post, Delete
	
	@PostMapping
	@ResponseBody 
	public PersonDto addPerson(@RequestBody PersonDto person) {
		return PersonService.addPerson(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody 
	public Optional<PersonDto> deletePerson(@PathVariable("id") int idPerson) {
		return PersonService.deletePerson(idPerson);
	}	
	
	@PutMapping("/modify")
	@ResponseBody 
	public Optional<PersonDto> modifyPerson(@RequestBody PersonDto person) {
		return PersonService.modifyPerson(person);
	}
	
	@PutMapping("/setNationality")
	@ResponseBody
	public Optional<PersonDto> setNationality(@RequestParam("id") int idPerson,
										   @RequestParam("n") String nationality){
		return PersonService.setNationality(idPerson, Nationality.valueOf(nationality));
	}
}
	