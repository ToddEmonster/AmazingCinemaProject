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
				 new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
				 new Person("Gérard Darmon", LocalDate.of(1948, 02, 29)),
				 new Person("Todd Phillips", LocalDate.of(1970, 12, 20)),
				 new Person("Clint Eastwood", LocalDate.of(1970, 12, 20)),
				 new Person("Marty Scorsese", LocalDate.of(1970, 12, 20))
				);
		
		persons.forEach(entityManager::persist);
		//when
		var dataRead = repoPerson.findByName("Clint Eastwood");
		System.out.println("find by name => " + dataRead);
		
	}

}
