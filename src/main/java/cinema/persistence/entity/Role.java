package cinema.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	private RoleId pk;
	private String nameRole;
	
	public Role() {
		this(null, null);
	}
	
	public Role(RoleId pk, String nameRole) {
		super();
		this.pk = pk;
		this.nameRole = nameRole;
	}
	
	@Id
	@Embedded
	public RoleId getPk() {
		return pk;
	}
	
	public void setPk(RoleId pk) {
		this.pk = pk;
	}
	
	@Column(name = "name_role")
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
	@ManyToOne
//	@JoinColumn(name = "id_movie", nullable = false)
	public Movie getMovie() {
		return getPk().getMovie();
	}
	
	public void setMovie(Movie movie) {
		getPk().setMovie(movie);
	}
	
	@ManyToOne
//	@JoinColumn(name = "id_actor", nullable = false)
	public Person getActor() {
		return getPk().getActor();
	}

	public void setActor(Person actor) {
		getPk().setActor(actor);
	}

}


