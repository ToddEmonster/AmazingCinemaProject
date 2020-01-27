package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Person;
import cinema.persistence.repository.PersonRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestPerson {

	@Autowired
	PersonRepository repoPerson;
	
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	void testFindByName() {
		//given
		
		List<Person> persons = List.of(
				 new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28), 
						 "Joaquin Phoenix was born Joaquin Rafael Bottom in San Juan, Puerto Rico, to Arlyn (Dunetz) and John Bottom,"
						 + " and is the middle child in a brood of five. His parents, from the continental United States, were then serving as Children of God missionaries."),
				 new Person("Gérard Darmon", LocalDate.of(1948, 02, 29),
						 "Gérard Darmon was born on February 29, 1948 in Paris, France. He is an actor, known for Astérix & Obélix: Mission Cléopâtre (2002), 37°2 le matin (1986) and Pasport (1990)."),
				 new Person("Todd Phillips", LocalDate.of(1970, 12, 20),
						 "Todd Phillips was born on December 20, 1970 in Brooklyn, New York City, New York, USA as Todd Bunzl. "),
				 new Person("Clint Eastwood", LocalDate.of(1970, 12, 20),
						 "Clint Eastwood was born May 31, 1930 in San Francisco, the son of Clinton Eastwood Sr., a manufacturing executive for Georgia-Pacific Corporation, and Ruth Wood, a housewife turned IBM operator."),
				 new Person("Marty Scorsese", LocalDate.of(1970, 12, 20),
						 "Martin Charles Scorsese was born on November 17, 1942 in Queens, New York City, to Catherine Scorsese (née Cappa) and Charles Scorsese.")
				);
		
		persons.forEach(entityManager::persist);
		//when
		var dataRead = repoPerson.findByName("Clint Eastwood");
		System.out.println("find by name => " + dataRead);
		
	}

}
