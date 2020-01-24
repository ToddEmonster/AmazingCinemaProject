package cinema.service;

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
import cinema.service.impl.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService{
	
	@Autowired
	MovieRepository movieRepository;

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		// TODO Auto-generated method stub
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getMovieByPartialTitle(String partialTitle) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitleIgnoreCaseContaining(partialTitle);
	}

	@Override
	public Set<Movie> getByYearBetween(int year1, int year2) {
		return  movieRepository.findByYearBetween(year1, year2);
	}

	@Override
	public Set<Movie> getByDirector(String directorName) {
		return movieRepository.findByDirectorName(directorName );
	}

	@Override
	public Set<Movie> getByDirector(int idDirector) {
		 var optDirector = personRepository.findById(idDirector);
		return optDirector.map(d -> movieRepository.findByDirector(d)).orElseGet(()-> Collections.emptySet());
	}

	@Override
	public Set<Movie> getByActor(int idActor) {
		return movieRepository.findByActorsIdPerson(idActor );
	}

	@Override
	public Set<Movie> getByActorOrDirector(String actorName, String directorName) {
		return  movieRepository.findByActorsNameOrDirectorName( actorName,  directorName );
	}
	
}
