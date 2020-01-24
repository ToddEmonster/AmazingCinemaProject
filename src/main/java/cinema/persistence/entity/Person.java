package cinema.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	
	private Integer idPerson;
	private String name;
	private LocalDate birthDate;
	
	public Person(Integer idPerson, String name, LocalDate birthDate) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Person(String name, LocalDate birthDate) {
		this(null, name, birthDate);
	}
	
	public Person(String name) {
		this(null, name, null);
	}
	
	public Person() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	@Column(nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		return builder.append(" (").append(birthDate).append("). #").append(idPerson).toString();
	}
	
}
