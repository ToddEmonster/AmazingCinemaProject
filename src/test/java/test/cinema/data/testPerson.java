package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.data.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;


class testPerson {


	
	@Test
	void testCreate() {
		List<Person> persons = List.of(
	    new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),
		new Person("G�rard Darmon", LocalDate.of(1948, 02, 29)),
		new Person("Todd Phillips", LocalDate.of(1970, 12, 20)));
		
	}

}
