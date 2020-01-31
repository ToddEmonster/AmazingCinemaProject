package cinema.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.dto.MovieFull;
import cinema.dto.MovieLight;
import cinema.dto.PersonDto;
import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.IMovieService;
import utils.DtoUtils;

//@Transactional : veut dire que toutes les méthodes doivent être commit
// donc pas besoin de flush
@Service
@Transactional 
public class MovieService implements IMovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper mapper;
	
	
	/*
	 * READ
	 */
	@Override
	public List<MovieLight> getAllMovies() {
		// Methode Utils avec list
		return DtoUtils.listFromEntityStream(
				movieRepository.findAll().stream(), 
				mapper, MovieLight.class);
	}

	@Override
	public Set<MovieLight> getMoviesByTitle(String title) {
		// Methode Utils avec set
		return DtoUtils.collectionFromEntityStream(
				movieRepository.findByTitle(title).stream(), 
				mapper, 
				MovieLight.class,
				HashSet::new);
	}
	
	@Override
	public Set<MovieLight> getMoviesByPartialTitle(String partialTitle) {
		// Methode Utils avec collection->Set
		return DtoUtils.setFromEntityStream(
				movieRepository.findByTitleContainingIgnoreCase(partialTitle)
					.stream(),
				mapper,
				MovieLight.class);
	}
	
	@Override
	public Set<MovieLight> getMovieByTitleContainingIgnoreCaseAndYear(String partialTitle, int year) {
		// Methode traditionnelle avec stream, ModelMapper, Collect
		return movieRepository.findByTitleContainingIgnoreCaseAndYear(partialTitle, year)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<MovieLight> getMoviesByOriginalTitleContainingIgnoreCase(String partialTitle) {
		return DtoUtils.setFromEntityStream(
				movieRepository.findByOriginalTitleContainingIgnoreCase(partialTitle)
					.stream(), 
				mapper, 
				MovieLight.class);
	}

	@Override
	public Optional<MovieFull> getMovieById(int idMovie) {
		return movieRepository.findById(idMovie)
				.map(me -> mapper.map(me, MovieFull.class));
	}

	@Override
	public Set<MovieLight> getMoviesByDirector(int idDirector) {
		var directorOpt = personRepository.findById(idDirector);
		// methode fonctionnelle
		return directorOpt.map(d ->  movieRepository.findByDirector(d))
				.orElseGet(()->Collections.emptySet())
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<MovieLight> getMoviesByYear(int year) {
		return movieRepository.findByYear(year)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<MovieLight> getMoviesByActorsIdPerson(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<MovieLight> getMoviesByActorsNameEndingWithIgnoreCase(String nameActor) {
		return movieRepository.findByActorsNameEndingWithIgnoreCase(nameActor)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<MovieLight> getMoviesByClassification(String classification) {
		return movieRepository.findByClassification(classification)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}
	

	@Override
	public Set<MovieLight> getMoviesByColorMode(ColorMode colorMode) {
		return movieRepository.findByColorMode(colorMode)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}


	@Override
	public Set<MovieLight> getMoviesByGenre(Genre genre) {
		return movieRepository.findByGenre(genre)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<MovieLight> getMoviesByRating(float rating) {
		return movieRepository.findByRating(rating)
				.stream()
				.map(me->mapper.map(me, MovieLight.class))
				.collect(Collectors.toSet());
	}
	
	
	/*
	 * CREATE, UPDATE, DELETE
	 */
	@Override
	public MovieFull addMovie(MovieFull movieDto) {
		Movie movieSaved = mapper.map(movieDto, Movie.class);
		movieRepository.save(movieSaved);
		mapper.map(movieSaved, movieDto); 
		return movieDto;
	}
	
	@Override
	public Optional<MovieFull> modifyMovie(MovieFull movie) {
		Optional<Movie> optMovie = movieRepository.findById(movie.getIdMovie());
		optMovie.ifPresent(m -> {
			m.setTitle(movie.getTitle());
			m.setYear(movie.getYear());
			
			m.setOriginalTitle(movie.getOriginalTitle());
			m.setDuration(movie.getDuration());
			m.setDirector(movie.getDirector());
			m.setActors(movie.getActors());
			m.setSynopsis(movie.getSynopsis());
			m.setClassification(movie.getClassification());
			m.setColorMode(movie.getColorMode());
			m.setGenre(movie.getGenre());
			m.setRating(movie.getRating());
			
			movieRepository.flush();
		});
		return optMovie.map(me->mapper.map(me, MovieFull.class));
	}
	
	@Override
	public Optional<MovieFull> deleteMovie(int idMovie) {
		Optional<Movie> movieToDelete = movieRepository.findById(idMovie);
		movieToDelete.ifPresent(m -> {
			movieRepository.delete(m);
			movieRepository.flush();
		});
		return movieToDelete.map(me->mapper.map(me, MovieFull.class));
	}
	


	@Override
	public Optional<MovieFull> addActor(int idActor, int idMovie) {
		// Programmation fonctionnelle
		// si y'a pas de données en rentrée, renvoie direct Optional.empty,
		//     equivalent de ifPresent, il le fait tout seul
		// flatMap permet de ne garder que l'emboitement du niveau le plus élevé,
		//     vu qu'on est en imbrication
		return movieRepository.findById(idMovie)
				.flatMap(me->personRepository.findById(idActor)
							.map(a-> { 
								me.getActors().add(a);
								return mapper.map(me, MovieFull.class);
								})
					);	
//		// méthode traditionnelle
//		if (movieOpt.isPresent() && actorOpt.isPresent()) {
//			movieOpt.get().getActors().add(actorOpt.get());
//			return Optional.of(mapper.map(movieOpt.get(), MovieFull.class));
//		}
//		return Optional.empty();
	}

	
	@Override
	public Optional<MovieFull> setDirector(int idDirector, int idMovie) {
		Optional<Movie> movieOpt = movieRepository.findById(idMovie);
		Optional<Person> directorOpt = personRepository.findById(idDirector);
		
		if (movieOpt.isPresent() && directorOpt.isPresent()) {
			movieOpt.get().setDirector(directorOpt.get());
			movieRepository.flush();
		}
		return movieOpt.map(me->mapper.map(me, MovieFull.class));
	}

	
	


	@Override
	public Optional<MovieFull> setRating(int idMovie, float rating) {
		Optional<Movie> movieOptToRate = movieRepository.findById(idMovie);
		movieOptToRate.ifPresent(m -> {
			movieOptToRate.get().setRating(rating);
			movieRepository.flush();
		});
		return movieOptToRate.map(me->mapper.map(me, MovieFull.class));
	}

	@Override
	public Optional<MovieFull> setClassification(int idMovie, String classification) {
		Optional<Movie> movieOptToClassify= movieRepository.findById(idMovie);
		movieOptToClassify.ifPresent(m -> {
			movieOptToClassify.get().setClassification(classification);
			movieRepository.flush();
		});
		return movieOptToClassify.map(me->mapper.map(me, MovieFull.class));
	}
	

	@Override
	public Optional<MovieFull> setSynopsis(int idMovie, String synopsis) {
		Optional<Movie> movieOptToResume= movieRepository.findById(idMovie);
		movieOptToResume.ifPresent(m -> {
			movieOptToResume.get().setSynopsis(synopsis);
			movieRepository.flush();
		});
		return movieOptToResume.map(me->mapper.map(me, MovieFull.class));
	}
	
	@Override
	public Optional<MovieFull> setColorMode(int idMovie, ColorMode colorMode) {
		Optional<Movie> movieOptToColored= movieRepository.findById(idMovie);
		movieOptToColored.ifPresent(m -> {
			movieOptToColored.get().setColorMode(colorMode);
			movieRepository.flush();
		});
		return movieOptToColored.map(me->mapper.map(me, MovieFull.class));
	}


	@Override
	public Optional<MovieFull> setGenre(int idMovie, List<Genre> genre) {
		Optional<Movie> movieOptToGenre= movieRepository.findById(idMovie);
		movieOptToGenre.ifPresent(m -> {
			movieOptToGenre.get().setGenre(genre);
			movieRepository.flush();
		});
		return movieOptToGenre.map(me->mapper.map(me, MovieFull.class));
	}





	
	
}
