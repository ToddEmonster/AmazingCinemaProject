package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.Movie;

public interface IMovieService {
	List<Movie> getAllMovies();
	Optional<Movie> getMovieById(int idMovie);
	Set<Movie> getMoviesByPartialTitle(String partialTitle);
	Set<Movie> getMoviesByTitle(String title);
//	Set<Movie> getMovieByPartialOriginalTitle(String partialTitle);
	Set<Movie> getMoviesByOriginalTitleContainingIgnoreCase(String partialTitle);
	
	Set<Movie> getMoviesByDirector(int idDirector);
	Set<Movie> getMoviesByActorsIdPerson(int idPerson);
	Set<Movie> getMoviesByYear(int year);
//	Set<Movie> getMoviesByPartialTitleAndYear(String title, int year);
	Set<Movie> getMoviesByActorsNameEndingWithIgnoreCase(String name);
	Set<Movie> getMovieByTitleContainingIgnoreCaseAndYear(String name, int year);
	
	Movie addMovie(Movie movie);
	Optional<Movie> addActor(int idActor, int idMovie);
	Optional<Movie> setDirector(int idDirector, int idMovie);
	Optional<Movie> modifyMovie(Movie movie);
	Optional<Movie> deleteMovie(int idMovie);
	
//	Set<Movie> getMoviesByGenre(String genre);
//	Optional<Movie> addGenre(int idMovie, String genre);
//	Optional<Movie> deleteGenre(int idMovie, String genre);
	
	Set<Movie> getMoviesByRating(float rating);
	Optional<Movie> setRating(int idMovie, float rating);
	Set<Movie> getMoviesByClassification(String classification);
	Optional<Movie> setClassification(int idMovie, String classification);
}
