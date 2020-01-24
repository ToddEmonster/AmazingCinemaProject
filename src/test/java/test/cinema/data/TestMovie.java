package test.cinema.data;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestMovie{

	@Test
	void testOne() {
		
		Movie joker = new Movie("Jokerrrr", 2019, 165);
		joker.setTitle("Joker");
		System.out.println(joker.getTitle());

		Movie godfather = new Movie("The Godfather", 1972, 220);
		System.out.println(godfather.getTitle());

		Movie departed = new Movie("The Departed", 2006);
		System.out.println(departed.getTitle());
		System.out.println("");
		

		System.out.println(joker.toString());
		System.out.println(godfather.toString());
		System.out.println(departed.toString());
		
		List<Movie> movies = List.of(joker, godfather, departed);
		Movie oneMovie = movies.get(1);
		System.out.println(oneMovie);
	}
	
	@Test
	void testEquals() {
		Movie joker = new Movie("Jokerrrr", 2019, 165);
		Movie godfather = new Movie("The Godfather", 1972, 220);
		Movie movie = joker;
		System.out.println(joker == godfather);
		System.out.println(movie == joker);
		
	}

	@Test
	void testEquals2() {
		Movie chaos1 = new Movie("Chaos", 2005);
		Movie chaos2 = new Movie("Chaos", 2005);
		System.out.println(chaos1 == chaos2);
		System.out.println(chaos1.equals(chaos2));
	}

	@Test
	void testEqualsNull() {
		Movie chaos1 = new Movie("Chaos", 2005);
		Movie chaosNull = null;
		System.out.println(chaos1 == chaosNull);
		System.out.println(chaos1.equals(chaosNull));
		System.out.println(chaosNull.equals(chaos1));
	}

	@Test
	void testEqualsTitleNull() {
		Movie chaos1 = new Movie("Chaos", 2005);
		Movie movieTitleNull = new Movie(null, 2005);
		System.out.println(chaos1 == movieTitleNull);
		System.out.println(movieTitleNull == chaos1);
		System.out.println(chaos1.equals(movieTitleNull));
		System.out.println(movieTitleNull.equals(chaos1));
	}

	@Test
	void testMovieAsObject() {
		Movie chaos1 = new Movie("Chaos", 2005);
		Object obj = chaos1;
		Movie movie = (Movie) obj;
		System.out.println(movie.getTitle());
	}

	@Test
	void testCreateData() {
		Movie joker = new Movie("Joker", 2019);
		
		//add directors
		  // todd phillips
		Person todd = new Person("Todd Phillips");
		joker.setDirector(todd);
		System.out.println(joker + ", directed by " + joker.getDirector());
		
		  //clint eastwood
		Person clint = new Person("Clint Eastwood", LocalDate.of(1930, 05, 31));
		System.out.println(clint + " is " + clint.getAge().getAsInt() + " years old");
		Movie movieGT = new Movie("Gran Torino", 2008, 116, clint);
		System.out.println(movieGT);
		Movie movieUnforgiven = new Movie("Unforgiven", 1992, clint);
		System.out.println(movieUnforgiven+ ", directed by " + movieUnforgiven.getDirector());
	}
	

}
