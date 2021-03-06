package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;
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

	
	/*
	 * READ
	 */
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Set<Movie> getMoviesByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
	
	@Override
	public Set<Movie> getMoviesByPartialTitle(String partialTitle) {
		return movieRepository.findByTitleContainingIgnoreCase(partialTitle);
	}

	@Override
	public Set<Movie> getMoviesByOriginalTitleContainingIgnoreCase(String partialTitle) {
		return movieRepository.findByOriginalTitleContainingIgnoreCase(partialTitle);
	}
	
	@Override
	public Set<Movie> getMovieByTitleContainingIgnoreCaseAndYear(String partialTitle, int year) {
		return movieRepository.findByTitleContainingIgnoreCaseAndYear(partialTitle, year);
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

	@Override
	public Set<Movie> getMoviesByActorsIdPerson(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor);
	}
	
	@Override
	public Set<Movie> getMoviesByActorsNameEndingWithIgnoreCase(String nameActor) {
		return movieRepository.findByActorsNameEndingWithIgnoreCase(nameActor);
	}

	@Override
	public Set<Movie> getMoviesByClassification(String classification) {
		return movieRepository.findByClassification(classification);
	}
	

	@Override
	public Set<Movie> getMoviesByColorMode( ColorMode colorMode) {
		return movieRepository.findByColorMode(colorMode);
	}


	@Override
	public Set<Movie> getMoviesByGenre(Genre genre) {
		return movieRepository.findByGenre(genre);
	}

	
	/*
	 * CREATE / UPDATE
	 */
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
	public Set<Movie> getMoviesByRating(float rating) {
		return movieRepository.findByRating(rating);
	}

	@Override
	public Optional<Movie> setRating(int idMovie, float rating) {
		var movieOptToRate = movieRepository.findById(idMovie);
		movieOptToRate.ifPresent(m -> {
			movieOptToRate.get().setRating(rating);
			movieRepository.flush();
		});
		return movieOptToRate;
	}

	@Override
	public Optional<Movie> setClassification(int idMovie, String classification) {
		var movieOptToClassify= movieRepository.findById(idMovie);
		movieOptToClassify.ifPresent(m -> {
			movieOptToClassify.get().setClassification(classification);
			movieRepository.flush();
		});
		return movieOptToClassify;
	}
	

	@Override
	public Optional<Movie> setSynopsis(int idMovie, String synopsis) {
		var movieOptToResume= movieRepository.findById(idMovie);
		movieOptToResume.ifPresent(m -> {
			movieOptToResume.get().setSynopsis(synopsis);
			movieRepository.flush();
		});
		return movieOptToResume;
	}
	
	@Override
	public Optional<Movie> setColorMode(int idMovie, List<ColorMode> colorMode) {
		var movieOptToColored= movieRepository.findById(idMovie);
		movieOptToColored.ifPresent(m -> {
			movieOptToColored.get().setColorMode(colorMode);
			movieRepository.flush();
		});
		return movieOptToColored;
	}


	@Override
	public Optional<Movie> setGenre(int idMovie, List<Genre> genre) {
		var movieOptToGenre= movieRepository.findById(idMovie);
		movieOptToGenre.ifPresent(m -> {
			movieOptToGenre.get().setGenre(genre);
			movieRepository.flush();
		});
		return movieOptToGenre;
	}


	/*
	 * DELETE
	 */
	@Override
	public Optional<Movie> deleteMovie(int idMovie) {
		var movieToDelete = movieRepository.findById(idMovie);
		movieToDelete.ifPresent(m -> {
			movieRepository.delete(m);
			movieRepository.flush();
		});
		return movieToDelete;
	}


	
	

//	@Override
//	public Set<Movie> getMoviesByGenre(String genre) {
//		// TODO Auto-generated method stub
//		return movieRepository.findByGenres(genre);
//	}
//
//	@Override
//	public Optional<Movie> addGenre(int idMovie, String genre) {
//		// TODO Auto-generated method stub
////		var genres = movieRepository.findById(idMovie).get().getGenres();
//		
//		
//		return null;
//	}
//
//	@Override
//	public Optional<Movie> deleteGenre(int idMovie, String genre) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
}
