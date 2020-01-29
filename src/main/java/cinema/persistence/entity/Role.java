package cinema.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	private RoleId pk;
	private String nameRole;
	
	@Id
	public RoleId getIdRole() {
		return pk;
	}
	public void setIdRole(RoleId idRole) {
		this.pk = idRole;
	}
	
	@Column(name = "name_role")
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
	
	public Movie getMovie() {
		return getIdRole().getMovie();
	}
	
	public void setMovie(Movie movie) {
		getIdRole().setMovie(movie);
	}
	
	public Person getActor() {
		return getIdRole().getActor();
	}

	public void setActor(Person actor) {
		getIdRole().setActor(actor);
	}

}


