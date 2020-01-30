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

import cinema.persistence.entity.ColorMode;
import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Nationality;
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
	
	@Autowired
	EntityManager entityManager;
	
	@Rollback(false)
	@Test
	void initData() {
	
	Person joaquin = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 11),
			"Joaquin Phoenix was born Joaquin Rafael Bottom in San Juan, Puerto Rico, to Arlyn (Dunetz) and John Bottom, and is the middle child in a brood of five. His parents, from the continental United States, were then serving as Children of God missionaries.");
	Person gerard  = new Person("Gérard Darmon", LocalDate.of(1948, 02, 04),
			"Gérard Darmon was born on February 29, 1948 in Paris, France. He is an actor, known for Astérix & Obélix: Mission Cléopâtre (2002), 37°2 le matin (1986) and Pasport (1990).");
	Person todd    = new Person("Todd Phillips", LocalDate.of(1972, 12, 07),
			"Todd Phillips was born on December 20, 1970 in Brooklyn, New York City, New York, USA as Todd Bunzl.");
	Person clint   = new Person("Clint Eastwood", LocalDate.of(1940, 12, 14),
			"Clint Eastwood was born May 31, 1930 in San Francisco, the son of Clinton Eastwood Sr., a manufacturing executive for Georgia-Pacific Corporation, and Ruth Wood, a housewife turned IBM operator.");
	Person martin   = new Person("Martin Scorsese", LocalDate.of(1970, 12, 24),
			"Martin Charles Scorsese was born on November 17, 1942 in Queens, New York City, to Catherine Scorsese (née Cappa) and Charles Scorsese.");
	Person francis = new Person("Francis F Coppola", LocalDate.of(1940, 03, 17));
	Person gene    = new Person("Gene Hackmann", LocalDate.of(1940, 03, 17));
	Person morgan  = new Person("Morgan Freeman", LocalDate.of(1940, 03, 17));
	Person chris = new Person("Christopher Nolan", LocalDate.of(1962, 03, 17));
	
	List<Person> persons = List.of(joaquin, gerard, todd, clint, martin, francis, gene, morgan, chris);
	
	persons.forEach(repoPerson::save);

	Movie joker = new Movie("Joker","Joker", 2019, 124, todd,
		"In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker. ",
		"-12", ColorMode.COLORED, 8.9F);	
	Movie depart = new Movie("Les infiltrés","The departed", 2006, 136, martin, 
		"An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston. ",
		"-12", ColorMode.COLORED, 8.4F);	
	Movie god = new Movie("Le parrain","The godfather", 1972, 220, francis, 
		"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son. ",
		"-12", ColorMode.COLORED, 9.9F);	
	Movie apo = new Movie("Apocalypse now","Apocalypse now", 1979,220, francis, 
		"A U.S. Army officer serving in Vietnam is tasked with assassinating a renegade Special Forces Colonel who sees himself as a god. ",
		"-12", ColorMode.COLORED, 9.2F);
	Movie taxi = new Movie("Taxi driver","Taxi driver", 1976, 160, martin, 
		"A mentally unstable veteran works as a nighttime taxi driver in New York City, where the perceived decadence and sleaze fuels his urge for violent action by attempting to liberate a presidential campaign worker and an underage prostitute. ",
		"-16", ColorMode.COLORED, 8.1F);	
	Movie raging = new Movie("Raging bull","Raging bull", 1980, 144, martin,
		"The life of boxer Jake LaMotta, whose violence and temper that led him to the top in the ring destroyed his life outside of it. ",
		"-12", ColorMode.BLACK_WHITE, 8.7F);	
	Movie inter = new Movie("Interstellar","Interstellar", 2014, 142, chris,
		"A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
		"", ColorMode.COLORED, 8.9F);
	Movie unfor = new Movie("Impitoyable","Unforgiven", 1992, 128, clint,
		"Retired Old West gunslinger William Munny (Clint Eastwood) reluctantly takes on one last job, with the help of his old partner Ned Logan (Morgan Freeman) and a young man, The \"Schofield Kid\" (Jaimz Woolvett)",
		"-10", ColorMode.COLORED, 8.9F);
	Movie million = new Movie("Million dollar baby","Million dollar baby", 2004, 136, clint,
		"A determined woman works with a hardened boxing trainer to become a professional. ",
		"", ColorMode.COLORED, 8.2F);
	Movie mystic  = new Movie("Mystic river","Mystic river", 2014, 133, clint, 
		"The lives of three men who were childhood friends are shattered when one of them has a family tragedy. ",
		"-12", ColorMode.COLORED, 7.9F);

	List<Movie> movies = List.of(joker, depart, god, apo, taxi, raging, inter, unfor, million, mystic);
	
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
		Movie batman = new Movie("Batman:The dark knight, Le chevalier noir de la nuit noire","The dark knight", 2008, 147);
		repoMovie.save(batman);
//		repoMovie.flush();
		Person chris = (Person) repoPerson.findByNameContainingIgnoreCase("nolan").stream().findFirst().get();
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
		var data1 = repoPerson.findByBirthdateYear(1940);
		System.out.println(data1);
	}
	
	@Rollback(false)
	@Test
	void initialActorsListToMovie() {
		var unforgiven = repoMovie.findByTitle("Impitoyable").stream().findFirst().get();
		var clint = repoPerson.findByNameContainingIgnoreCase("Eastwood").stream().findFirst().get();
		var gene = repoPerson.findByNameContainingIgnoreCase("Hackmann").stream().findFirst().get();
		unforgiven.setActors(List.of(clint, gene));
		repoMovie.flush();
	}
	
	@Rollback(false)
	@Test
	void addActorsToMovie() {
		var unforgiven = repoMovie.findByTitle("Impitoyable").stream().findFirst().get();
		var clint = repoPerson.findByNameContainingIgnoreCase("Eastwood").stream().findFirst().get();
		var gene = repoPerson.findByNameContainingIgnoreCase("Hackmann").stream().findFirst().get();
		unforgiven.setActors(List.of(clint, gene));
		repoMovie.flush();
	}
	
	@Test
	void testLazyActors() {
		// read a movie : select the movie + it's director
		var unforgiven = repoMovie.findByTitle("Impitoyable").stream().findFirst().get();
		var morgan= repoPerson.findByNameContainingIgnoreCase("Freeman").stream().findFirst().get();
		unforgiven.getActors().add(morgan);		
		repoMovie.flush();
	}
	
	@Rollback(false)
//	@Test
	void AddPersons() {
		Person martin   = new Person("Martin Scorsese", LocalDate.of(1970, 12, 24),List.of(Nationality.US, Nationality.IT));
		var joon = new Person("Bong Joon Ho", LocalDate.of(1969, 9, 14), List.of(Nationality.KR));
		List<Person> persons = List.of(joon, martin);
		persons.forEach(repoPerson::save);		
	}

	
	@Test
	void testKevinBacon() {
		// given
		var kevin = new Person("Kevin Bacon", LocalDate.of(1958, 7, 8)) ;
		var meryl = new Person("Meryl Streep");
		var antony = new Person("Anthony Stewart Head");
		var burton = new Person("Tim Burton");
		var wynona = new Person("Wynona Rider");
		var portman = new Person("Natalie Portman");
		var reno = new Person("Jean Reno");
		var lemercier = new Person("Valérie Lemercier");
		var persons = List.of(kevin, meryl, antony, burton, wynona, portman, reno, lemercier);
		persons.forEach(entityManager::persist);
		
		var riviere = new Movie("La rivière sauvage", 1994);
		var dameFer = new Movie("La dame de Fer", 2011);
		var sweeney = new Movie("Sweeney Todd", 2007);
		var beetlejuice = new Movie("Beetlejuice", 1988);
		var blackSwan = new Movie("Black Swan", 2011);
		var leon = new Movie("Léon", 1994);
		var visiteurs = new Movie("Les visiteurs", 1993);
		var movies = List.of(riviere, dameFer, sweeney, beetlejuice, blackSwan, leon, visiteurs);
		movies.forEach(entityManager::persist);
		
		entityManager.flush();
		
		// when
		
		
		// then
		
	}
	
	
}











