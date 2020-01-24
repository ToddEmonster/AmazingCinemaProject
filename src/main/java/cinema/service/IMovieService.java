package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Movie;

public interface IMovieService {
	List<Movie> getAllMovies();
	Optional<Movie> getMovieById(int idMovie);
	Set<Movie> getMovieByPartialTitle(String partialTitle);
	Set<Movie> getMoviesByDirector(int idDirector);
	Set<Movie> getMoviesByActorsIdPerson(int idPerson);
	Set<Movie> getMoviesByActorsNameEndingWithIgnoreCase(String name);
	
	Movie addMovie(Movie movie);
	Optional<Movie> addActor(int idActor, int idMovie);
	Optional<Movie> setDirector(int idDirector, int idMovie);
	Optional<Movie> modifyMovie(Movie movie);
	Optional<Movie> deleteMovie(int idMovie);
}
