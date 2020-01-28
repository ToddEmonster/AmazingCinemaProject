package cinema.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Genre;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Set<Movie> findByTitle(String title);
	Set<Movie> findByYearGreaterThan(int year1);
	Set<Movie> findByTitleAndYear(String title, Integer year1);
	Set<Movie> findByYearBetween(Integer year1, Integer year2);
	Set<Movie> findByTitleContainingIgnoreCase(String title);
	Set<Movie> findByOriginalTitleContainingIgnoreCase(String title);
	Set<Movie> findByDirector(Person director);
	Set<Movie> findByDirectorNameEndingWith(String name);
	Set<Movie> findByActorsNameEndingWithIgnoreCase(String name);
	Set<Movie> findByDirectorNameEndingWithIgnoreCase(String name);
//	Set<Movie> findByTitleIgnoreCaseContaining(String name);
	Set<Movie> findByDirectorName(String name);
//	Set<Movie> findByDirector(int idDirector);
	Set<Movie> findByActorsNameOrDirectorName(String actorName, String directorName );
	Set<Movie> findByActorsIdPerson(int idActor);
	Set<Movie> findByTitleContainingIgnoreCaseAndYear(String title, int year);
	Set<Movie> findByYear(int year);
	
	Set<Movie> findByRating(float rating);
	Set<Movie> findByClassification(String classification);
	Set<Movie> findByColorMode(ColorMode colorMode);
	Set<Movie> findByGenre(Genre genre);
}
