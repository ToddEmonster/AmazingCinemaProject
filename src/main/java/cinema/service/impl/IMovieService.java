package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import cinema.persistence.entity.Movie;

public interface IMovieService {
	
	public List<Movie> getAllMovies ();
	public Optional<Movie> getMovieById(int idMovie); 
	public Set<Movie> getMovieByPartialTitle(String partialTitle);
	public Set<Movie> getByYearBetween(int year1,  int year2 );
	public Set<Movie> getByDirector(String directorName);
	public Set<Movie> getByDirector(int idDirector);
	public Set<Movie> getByActor(int idActor);
	public Set<Movie> getByActorOrDirector(String actorName,String directorName);
}

