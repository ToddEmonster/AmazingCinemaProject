package cinema.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	private LocalDate birthdate;
	private String biography;

	private List<Nationality> nationality = new ArrayList<Nationality>();
	
	public Person() {
		this(null, null);
	}
	public Person(Integer idPerson, String name, LocalDate birthdate, String biography, List<Nationality> nationality) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthdate = birthdate;
		this.biography = biography;
		this.nationality = nationality;
	}
	
	public Person(Integer idPerson, String name, LocalDate birthdate) {
		this(idPerson, name, birthdate, null, null);
	}
		
	public Person(String name, LocalDate birthdate, String biography) {
		this(null, name, birthdate, biography, null);
	}
	
	
	public Person(String name, LocalDate birthdate, List<Nationality> nationality) {
		this(null, name, birthdate, null, nationality);
	}
	
	public Person(String name, LocalDate birthdate) {
		this(null, name, birthdate);
	}
	
	public Person(String name) {
		this(null, name, null);
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Column(columnDefinition="TEXT")
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	@ElementCollection(targetClass=Nationality.class)
	public List<Nationality> getNationality() {
		return nationality;
	}

	public void setNationality(List<Nationality> nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		return builder.append(" (").append(birthdate).append("). #").append(idPerson).toString();
	}
	
}
