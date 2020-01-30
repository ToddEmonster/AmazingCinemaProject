package cinema.persistence.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RoleId implements java.io.Serializable {
	/***
	 * The composed key to Role 
	 */
	private static final long serialVersionUID = 1L;
	private Movie movie;
	private Person actor;
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false)
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_actor", nullable = false)
	public Person getActor() {
		return actor;
	}
	
	public void setActor(Person actor) {
		this.actor = actor;
	}
	
	
}
