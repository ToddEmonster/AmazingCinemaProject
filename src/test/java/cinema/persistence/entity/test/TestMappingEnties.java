package cinema.persistence.entity.test;
/**
 * this is not a unit test
 */

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEnties {
	
	@Autowired
	MovieRepository repoMovie;
	@Autowired
	PersonRepository repoPerson;
	
//	@Autowired
//	EntityManager entityManager;
	
	@Rollback(false)
	@Test
	void test() {
	
	Person joaquin = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 11));
	Person gerard  = new Person("Gérard Darmon", LocalDate.of(1948, 02, 04));
	Person todd    = new Person("Todd Phillips", LocalDate.of(1972, 12, 07));
	Person clint   = new Person("Clint Eastwood", LocalDate.of(1940, 12, 14));
	Person marty   = new Person("Marty Scorsese", LocalDate.of(1970, 12, 24));
	Person francis = new Person("Francis F Coppola", LocalDate.of(1940, 03, 17));
	Person gene    = new Person("Gene Hackmann", LocalDate.of(1940, 03, 17));
	Person morgan  = new Person("Morgan Freeman", LocalDate.of(1940, 03, 17));
	
	List<Person> persons = List.of(joaquin, gerard, todd, clint, marty, francis, gene, morgan);
	
	persons.forEach(repoPerson::save);

	Movie joker   = new Movie("Joker", 2019, todd);	
	Movie depart  = new Movie("The departed", 2006, 136, marty);	
	Movie god     = new Movie("The godfather", 1972, 220, francis);	
	Movie apo     = new Movie("Apocalypse now", 1979, francis);
	Movie taxi    = new Movie("Taxi driver", 1976, marty);	
	Movie raging  = new Movie("Raging bull", 1980, marty);	
	Movie inter   = new Movie("Interstellar", 2014);
	Movie unfor   = new Movie("Unforgiven", 1992, clint);
	Movie million = new Movie("Million dollar baby", 2004, clint);
	Movie mystic  = new Movie("Mystic river", 2014, clint);
	List<Movie> movies = List.of(joker, depart, god, taxi, raging, inter, unfor, million, mystic);
	
	movies.forEach(repoMovie::save);

	}
	
	@Rollback(false)
	@Test
	void testAddDirectorToExistingMovie() {
		var movies = repoMovie.findByTitle("Interstellar");
		if (movies.size() > 0) {
			var interstellar = movies.stream()
						.findFirst().get();		
			Person chris = new Person("Christopher Nolan", LocalDate.of(1962, 03, 17));
			repoPerson.save(chris);
			interstellar.setDirector(chris);
			}
		
	}
	
	@Rollback(false)
	@Test
	void testSelectMovieWithDirector() {
		var movies = repoMovie.findByTitle("Interstellar");
		if (movies.size() > 0) {
			var interstellar = movies.stream().findFirst().get();				
			var dir = interstellar.getDirector();
			System.out.println("*** ----------------- ***  " + dir);
		}
	}
	

	@Rollback(false)
	@Test
	void testAddMovies() {
		Movie batman = new Movie("The dark knight", 2008);
		repoMovie.save(batman);
//		repoMovie.flush();
		Person chris = (Person) repoPerson.findByNameIgnoreCaseContaining("nolan").stream().findFirst().get();
		batman.setDirector(chris);
		repoMovie.flush();
	}
	
	@Rollback(false)
	@Test
	void findMovieByDirector() {
		var data1 = repoMovie.findByDirectorNameEndingWithIgnoreCase("eastwood");
		System.out.println(data1);
	}

	@Test
	void findByBirth() {
		var data1 = repoPerson.findByBirthDateYear(1940);
		System.out.println(data1);
	}
	
	@Rollback(false)
	@Test
	void initialActorsListToMovie() {
		var unforgiven = repoMovie.findByTitle("Unforgiven").stream().findFirst().get();
		var clint = repoPerson.findByNameContaining("Eastwood").stream().findFirst().get();
		var gene = repoPerson.findByNameContaining("Hackmann").stream().findFirst().get();
		unforgiven.setActors(List.of(clint, gene));
		repoMovie.flush();
	}
	
	@Rollback(false)
	@Test
	void addActorsToMovie() {
		var unforgiven = repoMovie.findByTitle("Unforgiven").stream().findFirst().get();
		var clint = repoPerson.findByNameContaining("Eastwood").stream().findFirst().get();
		var gene = repoPerson.findByNameContaining("Hackmann").stream().findFirst().get();
		unforgiven.setActors(List.of(clint, gene));
		repoMovie.flush();
	}
	
	@Test
	void testLazyActors() {
		// read a movie : select the movie + it's director
		var unforgiven = repoMovie.findByTitle("Unforgiven").stream().findFirst().get();
		var morgan= repoPerson.findByNameContaining("Freeman").stream().findFirst().get();
		unforgiven.getActors().add(morgan);		
		repoMovie.flush();
	}
}











