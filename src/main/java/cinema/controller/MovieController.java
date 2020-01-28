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

import cinema.persistence.entity.Movie;
import cinema.service.IMovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
	
	@Autowired
	IMovieService MovieService;
	
	// Methodes Get
	
	@GetMapping
	@ResponseBody
	public List<Movie> allMovies() {
		return MovieService.getAllMovies();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id") int idMovie) {
		return MovieService.getMovieById(idMovie);
	}
	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByPartialTitle(@RequestParam("t") String partialTitle) {
		return MovieService.getMoviesByPartialTitle(partialTitle);
	}
	
	@GetMapping("/byFullTitle")
	@ResponseBody
	public Set<Movie> movieByTitle(@RequestParam("t") String title) {
		return MovieService.getMoviesByTitle(title);
	}
	
	@GetMapping("/byOriginalTitle")
	@ResponseBody
	public Set<Movie> movieByPartialOgiginalTitle(@RequestParam("t") String partialTitle) {
		return MovieService.getMoviesByOriginalTitleContainingIgnoreCase(partialTitle);
	}
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> findByDirector(@RequestParam("d") int idDirector) {
		return MovieService.getMoviesByDirector(idDirector);
	}

	@GetMapping("/byYear")
	@ResponseBody
	public Set<Movie> findByYear(@RequestParam("y") int year) {
		return MovieService.getMoviesByYear(year);
	}

	 /*
	  * getting errors with partial
	  */
	@GetMapping("/byTitleYear")
	@ResponseBody
	public Set<Movie> findByPartialTitleAndYear(@RequestParam("t") String title, @RequestParam("y") int year) {
		return MovieService.getMovieByTitleContainingIgnoreCaseAndYear(title, year);
	}
	
	@GetMapping("/byActorId")
	@ResponseBody
	public Set<Movie> findByActorId(@RequestParam("a") int idActor) {
		return MovieService.getMoviesByActorsIdPerson(idActor);
	}
	
	@GetMapping("/byActorName")
	public Set<Movie> findByActorName(@RequestParam("a") String nameActor) {
		return MovieService.getMoviesByActorsNameEndingWithIgnoreCase(nameActor);
	}	
	
	@GetMapping("/byRating")
	@ResponseBody
	public Set<Movie> findByRating(@RequestParam("r") float rating) {
		return MovieService.getMoviesByRating(rating);
	}
	
	@GetMapping("/byClassification")
	@ResponseBody
	public Set<Movie> findByClassification(@RequestParam("c") String classification) {
		return MovieService.getMoviesByClassification(classification);
	}
	
	
	
	// Methodes Put, Post, Delete
	@PostMapping
	@ResponseBody
	public Movie addMovie(@RequestBody Movie movie) {
		return MovieService.addMovie(movie);
	}
	
	
	// Dans un Controller, toujours renvoyer un truc et pas un void pour voir
	// Mettre un alias pour RequestParam permet de s'affranchir de l'ordre d'appel
	@PutMapping("/addActor")
	public Optional<Movie> addActor(@RequestParam("a") int idActor,
									@RequestParam("m") int idMovie) {
		return MovieService.addActor(idActor, idMovie);
	}
	
	@PutMapping("/setDirector")
	@ResponseBody
	public Optional<Movie> setDirector(@RequestParam("d") int idDirector,
									   @RequestParam("m") int idMovie) {
		return MovieService.setDirector(idDirector, idMovie);
	}
	
	@PutMapping("/modify")
	@ResponseBody
	public Optional<Movie> modifyMovie(@RequestBody Movie movie) {
		return MovieService.modifyMovie(movie);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<Movie> deleteMovie(@PathVariable("id") int idMovie) {
		return MovieService.deleteMovie(idMovie);
	}

	
	@PutMapping("/setRating")
	@ResponseBody
	public Optional<Movie> setRating(@RequestParam("m") int idMovie, 
									@RequestParam("r") float rating) {
		return MovieService.setRating(idMovie, rating);
	}
	
	@PutMapping("/setClassification")
	@ResponseBody
	public Optional<Movie> setClassification(@RequestParam("m") int idMovie, 
											@RequestParam("c") String classification) {
		return MovieService.setClassification(idMovie, classification);
	}

	
//	@GetMapping("/byGenre")
//	@ResponseBody
//	public Set<Movie> findByGenre(@RequestParam("g") String genre) {
//		return MovieService.getMoviesByGenre(genre);
//	}
	
	
	
	
}
