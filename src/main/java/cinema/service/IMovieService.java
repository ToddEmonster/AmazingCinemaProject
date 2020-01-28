package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;

public interface IMovieService {
	
	/*
	 * READ
	 */
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
	Set<Movie> getMoviesByRating(float rating);
	Set<Movie> getMoviesByClassification(String classification);
	Set<Movie> getMoviesByColorMode(ColorMode colorMode);
	Set<Movie> getMoviesByGenre(Genre genre);
	
	/*
	 * CREATE/UPDATE
	 */
	Movie addMovie(Movie movie);
	Optional<Movie> addActor(int idActor, int idMovie);
	Optional<Movie> setDirector(int idDirector, int idMovie);
	Optional<Movie> modifyMovie(Movie movie);
	Optional<Movie> deleteMovie(int idMovie);
	
	
	Optional<Movie> setRating(int idMovie, float rating);
	Optional<Movie> setClassification(int idMovie, String classification);
	Optional<Movie> setSynopsis(int idMovie, String synopsis);
	Optional<Movie> setColorMode(int idMovie, List<ColorMode> colorMode);
	Optional<Movie> setGenre(int idMovie, List<Genre> genre);
}
