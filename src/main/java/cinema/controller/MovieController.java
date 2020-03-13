package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import cinema.dto.MovieFull;
import cinema.dto.MovieLight;
import cinema.exception.MovieNotFoundException;
import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;
import cinema.service.IMovieService;

@CrossOrigin
@RestController
@RequestMapping("/api/movie")
public class MovieController {
	
	@Autowired
	IMovieService MovieService;
	
	// Methodes Get
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<MovieLight> allMovies() {
		return MovieService.getAllMovies();
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<MovieFull> movieById(@PathVariable("id") int idMovie) {
		Optional<MovieFull> fullMovie = MovieService.getMovieById(idMovie);
		if (fullMovie.isPresent()) {
			return fullMovie;
		}
		
		throw new MovieNotFoundException();
	
	}
	
	@CrossOrigin
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<MovieLight> movieByPartialTitle(@RequestParam("t") String partialTitle) {
		return MovieService.getMoviesByPartialTitle(partialTitle);
	}
	
	@CrossOrigin
	@GetMapping("/byFullTitle")
	@ResponseBody
	public Set<MovieLight> movieByFullTitle(@RequestParam("t") String title) {
		return MovieService.getMoviesByTitle(title);
	}
	
	@CrossOrigin
	@GetMapping("/byOriginalTitle")
	@ResponseBody
	public Set<MovieLight> movieByPartialOriginalTitle(@RequestParam("t") String partialTitle) {
		return MovieService.getMoviesByOriginalTitleContainingIgnoreCase(partialTitle);
	}
	
	@CrossOrigin
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<MovieLight> findByDirector(@RequestParam("d") int idDirector) {
		return MovieService.getMoviesByDirector(idDirector);
	}
	
	@CrossOrigin
	@GetMapping("/byYear")
	@ResponseBody
	public Set<MovieLight> findByYear(@RequestParam("y") int year) {
		return MovieService.getMoviesByYear(year);
	}

	@CrossOrigin
	@GetMapping("/byTitleYear")
	@ResponseBody
	public Set<MovieLight> findByPartialTitleAndYear(@RequestParam("t") String title, @RequestParam("y") int year) {
		return MovieService.getMovieByTitleContainingIgnoreCaseAndYear(title, year);
	}

	@CrossOrigin
	@GetMapping("/byActorId")
	@ResponseBody
	public Set<MovieLight> findByActorId(@RequestParam("a") int idActor) {
		return MovieService.getMoviesByActorsIdPerson(idActor);
	}

	@CrossOrigin
	@GetMapping("/byActorName")
	public Set<MovieLight> findByActorName(@RequestParam("a") String nameActor) {
		return MovieService.getMoviesByActorsNameEndingWithIgnoreCase(nameActor);
	}	

	@CrossOrigin
	@GetMapping("/byRating")
	@ResponseBody
	public Set<MovieLight> findByRating(@RequestParam("r") float rating) {
		return MovieService.getMoviesByRating(rating);
	}

	@CrossOrigin
	@GetMapping("/byColorMode")
	@ResponseBody
	public Set<MovieLight> findByColorMode(@RequestParam("c")  ColorMode colorMode) {
		return MovieService.getMoviesByColorMode(colorMode);
	}

	@CrossOrigin
	@GetMapping("/byClassification")
	@ResponseBody
	public Set<MovieLight> findByClassification(@RequestParam("c") String classification) {
		return MovieService.getMoviesByClassification(classification);
	}

	@CrossOrigin
	@GetMapping("/byGenre")
	@ResponseBody
	public Set<MovieLight> findByGenre(@RequestParam("g") Genre genre) {
		return MovieService.getMoviesByGenre(genre);
	}
	
	
	/*
	 * POST
	 */

	@CrossOrigin
	@PostMapping
	@ResponseBody
	public MovieFull addMovie(@RequestBody MovieFull movie) {
		return MovieService.addMovie(movie);
	}
	
	/*
	 * PUT
	 */
	// Dans un Controller, toujours renvoyer un truc et pas un void pour voir
	// Mettre un alias pour RequestParam permet de s'affranchir de l'ordre d'appel
	@CrossOrigin
	@PutMapping("/addActor")
	public Optional<MovieFull> addActor(@RequestParam("a") int idActor,
									    @RequestParam("m") int idMovie) {
		return MovieService.addActor(idActor, idMovie);
	}

	@CrossOrigin
	@PutMapping("/setDirector")
	@ResponseBody
	public Optional<MovieFull> setDirector(@RequestParam("d") int idDirector,
									   @RequestParam("m") int idMovie) {
		return MovieService.setDirector(idDirector, idMovie);
	}

	@CrossOrigin
	@PutMapping("/modify")
	@ResponseBody
	public Optional<MovieFull> modifyMovie(@RequestBody MovieFull movie) {
		return MovieService.modifyMovie(movie);
	}

	@CrossOrigin
	@PutMapping("/setRating")
	@ResponseBody
	public Optional<MovieFull> setRating(@RequestParam("m") int idMovie, 
									@RequestParam("r") float rating) {
		return MovieService.setRating(idMovie, rating);
	}

	@CrossOrigin
	@PutMapping("/setClassification")
	@ResponseBody
	public Optional<MovieFull> setClassification(@RequestParam("m") int idMovie, 
											@RequestParam("c") String classification) {
		return MovieService.setClassification(idMovie, classification);
	}

	@CrossOrigin
	@PutMapping("/setSynopsis")
	@ResponseBody
	public Optional<MovieFull> setSynopsis(@RequestParam("m") int idMovie, 
									   @RequestParam("s") String synopsis) {
		return MovieService.setSynopsis(idMovie, synopsis);
	}

	@CrossOrigin
	@PutMapping("/setColorMode")
	@ResponseBody
	public Optional<MovieFull> setColorMode(@RequestParam("m") int idMovie, 
										@RequestParam("c") ColorMode colorMode) {
		return MovieService.setColorMode(idMovie, colorMode);
	}

	@CrossOrigin
	@PutMapping("/setGenre")
	@ResponseBody
	public Optional<MovieFull> setGenre(@RequestParam("m") int idMovie, 
									@RequestParam("g") List<Genre> genre) {
		return MovieService.setGenre(idMovie, genre);
		// Dans les requêtes, se référer aux genres définis dans l'énumération.
		// Pas besoin de parenthèses, guillemets ou autre. Exemples :
		// g = THRILLER
		// g = ACTION, THRILLER
	}
	

	/*
	 * DELETE
	 */
	@CrossOrigin
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<MovieFull> deleteMovie(@PathVariable("id") int idMovie) {
		return MovieService.deleteMovie(idMovie);
	}

	
	
}
