package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Movie;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;

	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Set<Movie> getMovieByPartialTitle(String partialTitle) {
		return movieRepository.findByTitleContainingIgnoreCase(partialTitle);
	}
	
	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getMoviesByDirector(int idDirector) {
		var directorOpt = personRepository.findById(idDirector);
		// methode fonctionnelle
		return directorOpt.map(d ->  movieRepository.findByDirector(d))
				.orElseGet(()->Collections.emptySet());
	}
	
	@Override
	public Set<Movie> getMoviesByYear(int year) {
		return movieRepository.findByYear(year);
	}

//	@Override
//	public Set<Movie> getMoviesByPartialTitleAndYear(String title, int year) {
//		return movieRepository.findByPartialTitleAndYear(title, year);
//	}
//	
	@Override
	public Set<Movie> getMoviesByActorsIdPerson(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor);
	}
	
	@Override
	public Set<Movie> getMoviesByActorsNameEndingWithIgnoreCase(String nameActor) {
		return movieRepository.findByActorsNameEndingWithIgnoreCase(nameActor);
	}
	
	@Override
	public Movie addMovie(Movie movie) {
		Movie movieSaved = movieRepository.save(movie);
		movieRepository.flush();
		return movieSaved;
	}
	
	@Override
	public Optional<Movie> addActor(int idActor, int idMovie) {
		var movieOpt = movieRepository.findById(idMovie);
		var actorOpt = personRepository.findById(idActor);
		
		// méthode traditionnelle
		if (movieOpt.isPresent() && actorOpt.isPresent()) {
		movieOpt.get().getActors().add(actorOpt.get());
		movieRepository.flush();
		};
		return movieOpt;
	}
	
	@Override
	public Optional<Movie> setDirector(int idDirector, int idMovie) {
		var movieOpt = movieRepository.findById(idMovie);
		var directorOpt = personRepository.findById(idDirector);
		
		if (movieOpt.isPresent() && directorOpt.isPresent()) {
			movieOpt.get().setDirector(directorOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}
	
	@Override
	public Optional<Movie> modifyMovie(Movie movie) {
		var optMovie = movieRepository.findById(movie.getIdMovie());
		optMovie.ifPresent(m -> {
			m.setTitle(movie.getTitle());
			m.setYear(movie.getYear());
			m.setDuration(movie.getDuration());
			m.setDirector(movie.getDirector());
			movieRepository.flush();
		});
		//
		return optMovie;
	}
	
	@Override
	public Optional<Movie> deleteMovie(int idMovie) {
		var movieToDelete = movieRepository.findById(idMovie);
		movieToDelete.ifPresent(m -> {
			movieRepository.delete(m);
			movieRepository.flush();
		});
		return movieToDelete;
	}
	
}
