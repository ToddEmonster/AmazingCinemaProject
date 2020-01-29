package cinema.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {
	
	private Integer idPerson;
	private String name;
	private LocalDate birthdate;
	private String biography;

	private List<Nationality> nationality;
	
	private Set<Role> roles;
	
	
	public Person() {
		this(null, null);
	}
	
	public Person(Integer idPerson, String name, LocalDate birthdate, String biography) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthdate = birthdate;
		this.biography = biography;
		this.nationality = new ArrayList<Nationality>();
		this.roles = new HashSet<Role>();
	}
	
	public Person(Integer idPerson, String name, LocalDate birthdate) {
		this(idPerson, name, birthdate, null);
	}
		
	public Person(String name, LocalDate birthdate, String biography) {
		this(null, name, birthdate, biography);
	}
	
	
	public Person(String name, LocalDate birthdate) {
		this(null, name, birthdate, null);
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
	
	@OneToMany(mappedBy = "pk.actor")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		return builder.append(" (").append(birthdate).append("). #").append(idPerson).toString();
	}
	
}
