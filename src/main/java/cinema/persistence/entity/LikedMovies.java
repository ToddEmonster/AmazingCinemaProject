package cinema.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class LikedMovies {
	@EmbeddedId
	MovieLikingKey id;
	
	@ManyToOne
	@MapsId("account_id")
	@JoinColumn(name="account_id")
	Account account;

	@ManyToOne
	@MapsId("movie_id")
	@JoinColumn(name="movie_id")
	Movie movie;
}
