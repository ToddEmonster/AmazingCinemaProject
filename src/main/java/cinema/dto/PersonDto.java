package cinema.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import cinema.persistence.entity.Nationality;

public class PersonDto {
	
	private Integer idPerson;
	private String name;
	private LocalDate birthdate;
	private String biography;
	private List<Nationality> nationality;
		
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}
	
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
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	

	public List<Nationality> getNationality() {
		return nationality;
	}

	public void setNationality(List<Nationality> nationality) {
		this.nationality = nationality;
	}
	
	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknown")+ ")";
	}
	
}
