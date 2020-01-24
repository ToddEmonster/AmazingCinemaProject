package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.manipulation.Sorter;

import cinema.data.Movie;
import cinema.data.Person;

class TestCinema {

	private List<Movie> movies;
	private List<Person> persons;

	@BeforeEach
	void initData() {
		persons = new ArrayList<>();
		Collections.addAll(persons, 
				 new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
				 new Person("G�rard Darmon", LocalDate.of(1948, 02, 29)),
				 new Person("Clint Eastwood", LocalDate.of(1930, 05, 31)),
				 new Person("Todd Phillips", LocalDate.of(1970, 12, 20)),
				 new Person("Gene Hackman", LocalDate.of(1930, 06, 14)),
				 new Person("Morgan Freeman", LocalDate.of(1937, 02, 12))
				);
		
		var clint =  persons.get(3);		
		var todd =  persons.get(2);		

		movies = new ArrayList<>();
		Collections.addAll(movies, 
				new Movie("Unforgiven", 1992, 116, clint),
				new Movie("Interstallar", 2014, 120),
				new Movie("Gran Torino", 2009, clint),
				new Movie("Joker", 2019, todd ),
				new Movie("Hangover", 2009, 100, todd),
				new Movie("American Sniper", 2014, 133, clint),
				new Movie("Avengers", 2012, 140),
				new Movie("Avengers, Age of Ultron", 2016, 142),
				new Movie("Captain Marvel", 2018, 126),
				new Movie("Avengers, Endgame", 2019, 181),
				new Movie("Avengers, Infinity war", 2017, 133),
				new Movie("Parasite", 2019, 133)
				);
		
		movies.get(3).addActor(persons.get(0));
		movies.get(0).addAllActors(persons.get(5), persons.get(2), persons.get(4));
		var actorsParasite = List.of(
					new Person("Kang-ho Song", LocalDate.of(1988, 10, 06)),
					new Person("Yeo-jeong Jo", LocalDate.of(1984, 02, 24)),
					new Person("Wook-sik Choi", LocalDate.of(1994, 06, 02)),
					new Person("Jeon-eun Jee", LocalDate.of(1955, 11, 12))
				);
		persons.addAll(actorsParasite);
		movies.get(11).addAllActors(actorsParasite);
	}
	
	@Test
	void testModifiableList() {
		System.out.println(persons);
		System.out.println(persons.getClass());
		persons.add(new Person("Bradley Cooper", LocalDate.of(1975, 01, 05)));
		System.out.println(persons);
		System.out.println("movies : \n" + movies);
	}
	
	@Test
	void displayMovie() {
		// forI loop
		System.out.println("with for : ");
		for (int i = 0; i < movies.size(); i++) {
			var movie = movies.get(i);
			System.out.println("     " + movie + ", directed by " + movie.getDirector());
		}
		
		
		System.out.println("\nwith foreach : ");
		for (Movie movie : movies) {
			System.out.println("    " + movie + ", directed by " + movie.getDirector() );
		}
	}
	
	@Test
	void totalDurationClintMovies() {
		int totalDuration = 0;
		for (Movie movie : movies) {
			
			if (Objects.nonNull(movie.getDirector()) && movie.getDirector().getName() == "Clint Eastwood") {
				totalDuration += movie.getDuration();
			}
		}
		System.out.println("total duration : " + totalDuration);
	}

	////STREAM --- FILTER
	@Test
	void totalDurationClintMoviesStream() {
		var clint = persons.get(3);
		int totalDuration = movies.stream()
				.filter(m -> clint.equals(m.getDirector()))
				.mapToInt(Movie::getDuration)
//				.forEach(System.out::println);
				.sum();
		System.out.println("total duration : " + totalDuration);
	}
	
	@Test
	void orderedListAvengersStream() {
		var avengerOrdered = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.collect(Collectors.toCollection(()->new TreeSet<>(
					     Comparator.comparing(Movie::getReleaseDate))));
		System.out.println(avengerOrdered);
	}
	
	@Test
	void testTitlesAvengers() {
		var joinedTitles = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.map(Movie::getTitle)
				.collect(Collectors.joining(" -- "));
		System.out.println(joinedTitles);
	}
	
	@Test
	void testLimit() {
		movies.stream()
				.filter((Movie m) -> m.getReleaseDate() > 2000)
				.limit(3)
				.forEach(System.out::println);
	}
	
	
	@Test
	void testFirstYearAvengers() {
		OptionalInt firstYear = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.mapToInt(Movie::getReleaseDate)
				.min();
		System.out.println(firstYear);
	}
	
	@Test
	void testStatsAvengers() {
		var stats = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.mapToInt(Movie::getReleaseDate)
				.summaryStatistics();
		System.out.println("first year:" + stats.getMin());
		System.out.println("last year:" + stats.getMax());
		System.out.println(stats.toString());
	}
	
	@Test
	void testNbMoviesLonger120() {
		long countMovies = movies.stream()
				.filter((Movie m) -> m.getDuration() >= 120)
				.count();
		System.out.println(countMovies + " movies longer than 2h");
	}

	@Test
	void longestTitle() {
		var maxLength = movies.stream()
				.map(Movie::getTitle)
				.mapToInt(String::length)
				.max()
				.getAsInt();
		System.out.println(maxLength + " characters");
		var maxTitles = movies.stream()
			.map(Movie::getTitle)
			.filter(t ->t.length() == maxLength)
			.collect(Collectors.toList());
		System.out.println(maxTitles);
	}
	
	
	@Test
	void testSortedMovies() {
		SortedSet<Movie> sortedMovies = new TreeSet<>(
				Comparator.comparing(Movie::getReleaseDate, Comparator.reverseOrder())
				.thenComparing(Movie::getTitle));
//				(m1, m2) -> -1);
		sortedMovies.addAll(movies);
		System.out.println(movies);
	}

	@Test
	void testSortMovies() {
		Collections.sort(movies, Comparator.comparing(Movie::getReleaseDate).thenComparing(Movie::getTitle));
		System.out.println(movies);
	}
	
	///// MAP ////
	@Test
	void nbMovieByYear() {
		var res = movies.stream()
		.collect(Collectors.groupingBy(Movie::getReleaseDate, Collectors.counting()));
		System.out.println(res);
	}
	
	@Test
	void nbMovieByDirector() {
		var nbMovieByDirector = movies.stream()
				.filter(m -> Objects.nonNull(m.getDirector()))
				.collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
		System.out.println(nbMovieByDirector);
	}
	
	@Test
	void testFimo() {
		var filmographies = movies.stream()
				.filter(m -> Objects.nonNull(m.getDirector()))
				.collect(Collectors.groupingBy(Movie::getDirector,
						 Collectors.toCollection(
								 ()-> new TreeSet<>(Comparator.comparing(Movie::getReleaseDate,
										 Comparator.reverseOrder())))));
		System.out.println(filmographies);
	}
	
	@Test
	void directorByDecade() {
		var pers = persons.stream()
		.collect(Collectors.groupingBy((Person p) -> p.getBirthdate().getYear() / 10));
		System.out.println(pers);
	}
	
	@Test
	void testParasite() {
		movies.stream()
		.filter(m -> m.getTitle().equals("Parasite"))
		.findFirst()
		.ifPresent(System.out::println);
	}
	
	@Test
	void testParasiteStream() {
		var movie = movies.get(11);
		var actors = movie.streamActors()
		.map(Person::getName)
		.collect(Collectors.joining(", "));

		System.out.println(actors);
		
	}

	@Test
	void testParasiteIterable() {
		var movie = movies.get(11);
		for (var it =  movie.iteratorActor(); it.hasNext();) {
			var actor = it.next();
			System.out.println(actor);
		}
		
	}
}
