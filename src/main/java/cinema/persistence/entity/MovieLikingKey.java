package cinema.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovieLikingKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="account_id")
	Long accountId;
	
	@Column(name="movie_id")
	Long movieId;
	
	
}
