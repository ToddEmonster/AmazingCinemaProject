package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.MovieFull;
import cinema.dto.MovieLight;
import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;

public interface IMovieService {
	
	/*
	 * READ
	 */
	List<MovieLight> getAllMovies();
	Optional<MovieFull> getMovieById(int idMovie);
	Set<MovieLight> getMoviesByPartialTitle(String partialTitle);
	Set<MovieLight> getMoviesByTitle(String title);
//	Set<Movie> getMovieByPartialOriginalTitle(String partialTitle);
	Set<MovieLight> getMoviesByOriginalTitleContainingIgnoreCase(String partialTitle);
	
	Set<MovieLight> getMoviesByDirector(int idDirector);
	Set<MovieLight> getMoviesByActorsIdPerson(int idPerson);
	Set<MovieLight> getMoviesByYear(int year);
//	Set<Movie> getMoviesByPartialTitleAndYear(String title, int year);
	Set<MovieLight> getMoviesByActorsNameEndingWithIgnoreCase(String name);
	Set<MovieLight> getMovieByTitleContainingIgnoreCaseAndYear(String name, int year);
	Set<MovieLight> getMoviesByRating(float rating);
	Set<MovieLight> getMoviesByClassification(String classification);
	Set<MovieLight> getMoviesByColorMode(ColorMode colorMode);
	Set<MovieLight> getMoviesByGenre(Genre genre);
	
	/*
	 * CREATE/UPDATE
	 */
	MovieFull addMovie(MovieFull movie);
	Optional<MovieFull> modifyMovie(MovieFull movie);
	Optional<MovieFull> deleteMovie(int idMovie);
	Optional<MovieFull> addActor(int idActor, int idMovie);
	Optional<MovieFull> setDirector(int idDirector, int idMovie);
	
	
	Optional<MovieFull> setRating(int idMovie, float rating);
	Optional<MovieFull> setClassification(int idMovie, String classification);
	Optional<MovieFull> setSynopsis(int idMovie, String synopsis);
	Optional<MovieFull> setColorMode(int idMovie, ColorMode colorMode);
	Optional<MovieFull> setGenre(int idMovie, List<Genre> genre);
}
