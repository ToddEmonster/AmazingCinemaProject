package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMovie {

	@Autowired
	MovieRepository repoMovie;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	void testInsert() {
		Movie joker = new Movie("Joker", 2019);
		repoMovie.save(joker);
		var id = joker.getIdMovie();
		System.out.println("id new movie = " + id);
		assertNotNull(id);
	}
	
	@Test
	void testSelectAll() {
		//given
		List<Movie> data = List.of(
				new Movie("Joker", 2019),	
				new Movie("The departed", 2006, 136),	
				new Movie("The godfather", 1972, 220),	
				new Movie("Apocalypse now", 1979),	
				new Movie("Taxi driver", 1976),	
				new Movie("Raging bull", 1980),	
				new Movie("Interstellar", 2014)
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findAll();
		//then
//		dataRead.forEach(System.out::println);
		assertEquals(data.size(), dataRead.size());
		assertTrue(dataRead.stream()
				.map(Movie::getTitle)
				.allMatch(tr -> data.stream()
						.map(Movie::getTitle)
						.anyMatch(th -> th.equals(tr))
						));
	}
	
	@Test
	 void findOne() {
		//given
		Movie joker = new Movie("Joker", 2019);
		entityManager.persist(joker);
		var id = joker.getIdMovie();
		// when
		var movieReadOpt = repoMovie.findById(id);
		System.out.println(movieReadOpt);
		assertAll(
			()->assertTrue(movieReadOpt.isPresent()),
			()->assertEquals(joker.getTitle(), movieReadOpt.get().getTitle()));
	}
	
	@Test
	void testFindBy() {
		//given
		String titleLion = "The lion king";
		int year1 = 1994;
		int year2 = 2018;
		List<Movie> data = List.of(
				new Movie("Joker", 2019),	
				new Movie(titleLion, 1994, 136),	
				new Movie(titleLion, year2),	
				new Movie("Apocalypse now", year1)	
				);
		data.forEach(entityManager::persist);
		//when
		// by title
		var dataRead = repoMovie.findByTitle(titleLion);
		System.out.println("by title => " + dataRead);
		
		// greater than
		var dataYearGreater = repoMovie.findByYearGreaterThan(year1);
		System.out.println("year greater => " + dataYearGreater);

		// between
		var dataYearBetween = repoMovie.findByYearBetween(year1, year2);
		System.out.println("year between => " + dataYearBetween);
		assertAll(
        ()-> assertEquals(3, dataYearBetween.size()),
        ()-> assertTrue(dataYearBetween.stream()
			.mapToInt(Movie::getYear)
			.allMatch(y -> (y >= year1) && (y <= year2))));
		
	}

	@Test
	void testFindByTitleAndYear() {
		//given
		int year1 = 1994;
		String titleLion = "The lion king";
		List<Movie> data = List.of(
				new Movie("Joker", 2019),	
				new Movie(titleLion, year1, 136),	
				new Movie(titleLion, 2019),	
				new Movie("Apocalypse now", 1979)	
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findByTitleAndYear(titleLion, 1994);
		System.out.println(dataRead);
		
	}
	
	@Test
	void testSaveDirector() {
		//given
		Movie apo = new Movie("Apocalypse now", 1979);
		Person francis =  new Person("Francis Ford Coppola", LocalDate.of(1970, 12, 20));
		
		entityManager.persist(francis);
		//when
		repoMovie.save(apo);
		//then
		System.out.println(apo);
		System.out.println(francis);
	}
	
	@Test
	 void testfindByActorNameEndingWith() {
		//given
		var madmax = new Movie("Max max, fury road", 2015);
		var max    = new Movie("Mad max", 1978);
		var arme   = new Movie("L'arme fatale", 1987);
		var lion   = new Movie("Roi lion", 1978);
		var movies = List.of(madmax, max, arme, lion);
	    movies.forEach(entityManager::persist);
	    
	    var mel = new Person("Mel Gibson", LocalDate.of(1964, 02, 14));
	    var danny = new Person("Danny Glover", LocalDate.of(1956, 06, 21));
	    var whoopi = new Person("Whoopy Goldberg", LocalDate.of(1974, 8, 15));
	    entityManager.persist(mel);
	    entityManager.persist(whoopi);
	    entityManager.persist(danny);
	    
	    lion.getActors().add(danny);
	    max.getActors().add(mel);
	    madmax.getActors().add(mel);
	    Collections.addAll(arme.getActors(), mel, danny);
	    
	    entityManager.flush();
	    //when
	    var moviesWithMel = repoMovie.findByActorsNameEndingWithIgnoreCase("Gibson");
	    //then
	    assertAll(
	    		()->assertTrue(moviesWithMel.contains(madmax)),
	    		()->assertTrue(moviesWithMel.contains(max)),
	    		()->assertTrue(moviesWithMel.contains(arme)),
	    		()->assertFalse(moviesWithMel.contains(lion))
	    		);
	}
	
	@Test
	void testFindByRating() {
		// given
		var arme   = new Movie("L'arme fatale", 1987);
		var madmax = new Movie("Max max, fury road", 2015, 4.0F);
		var lion = new Movie("Roi lion", 1994, 5.0F);
		var joker = new Movie("Joker", 2019, 5.0F);
		
		var movies = List.of(arme, madmax, lion, joker);
		movies.forEach(entityManager::persist);
		
		entityManager.flush();
		// when
		var moviesGoodRating = repoMovie.findByRating(5.0F);
		
		// then
		assertAll(
				()->assertEquals(2, moviesGoodRating.size()),
				()->assertTrue(moviesGoodRating.contains(lion)),
				()->assertTrue(moviesGoodRating.contains(joker)),
				()->assertFalse(moviesGoodRating.contains(arme))
				);
	}
	
	@Test
	void testModifyRating() {
		// given
		var arme   = new Movie("L'arme fatale", 1987);
		var madmax = new Movie("Max max, fury road", 2015, 4.0F);
		var lion = new Movie("Roi lion", 1994, 5.0F);
		
		var movies = List.of(arme, madmax);
		movies.forEach(entityManager::persist);
		
		entityManager.flush();
		// when
		madmax.setRating(3.0F);
		
		
		// then
//		assertAll(
//				()->assertEquals(2, moviesGoodRating.size()),
//				()->assertTrue(moviesGoodRating.contains(lion)),
//				()->assertTrue(moviesGoodRating.contains(joker)),
//				()->assertFalse(moviesGoodRating.contains(arme))
//				);
	}
	
	
	@Test
	void testFindByGenre() {
		// TODO réussir à reconstruire le constructeur avec genre
		// given
		var lion   = new Movie("Roi lion", 1994);
		System.out.println(lion);
//		var madmax = new Movie("Max max, fury road", 2015);
//		var arme   = new Movie("L'arme fatale", 1987);
//		var joker  = new Movie("Joker", 2019);
//
//		var movies = List.of(madmax, arme, lion, joker);
//		movies.forEach(entityManager::persist);
//		
//		lion.setGenres(List.of("animation","enfants","disney"));
//		madmax.getGenres().add("action");
//		arme.getGenres().add("action");
//		
//		entityManager.flush();
//		
//		// when
//		var moviesAction = repoMovie.findByGenres("action");	
//		
//		// then
//		assertAll(
//				()->assertEquals(2, moviesAction.size()),
//				()->assertTrue(moviesAction.contains(madmax)),
//				()->assertTrue(moviesAction.contains(arme)),
//				()->assertFalse(moviesAction.contains(lion)),
//				()->assertFalse(moviesAction.contains(joker))
//				);
	}
	
}










