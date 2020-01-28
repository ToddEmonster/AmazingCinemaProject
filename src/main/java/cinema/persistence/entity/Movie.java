package cinema.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "movies")
public class Movie {
	
	private Integer idMovie;
	private String title;
	private String originalTitle;

	private Integer year;
	private Integer duration;

	private Person director;
	private List<Person> actors;
	private String synopsis;
	private ColorMode ColorMode;
	
	private Float rating;
	
	public Movie() {
		this(null, null);	
	}
	
	// Title, year
	public Movie(String title, Integer year) {
		this(null, title, null, year, null, null, null, null, null, null);
	}
	
	// Title, year, duration
	public Movie(String title, Integer year, Integer duration) {
		this(null, title, null, year, duration, null, null, null, null, null);
	}
	
	// Title, year, rating
	public Movie(String title, Integer year, Float rating) {
		this(null, title, null, year, null, null, null, null, null, rating);
	}
	
	public Movie(String title, String originalTitle, Integer year) {
		this(null,title, originalTitle, year, null, null, null, null, null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Integer duration) {
		this(null,title,originalTitle, year, duration, null,  null, null, null, null);
	}
	public Movie(String title, String originalTitle, Integer year, Person director) {
		this(null,title,originalTitle, year, null, director, null, null, null, null);
	}
	public Movie(String title, String originalTitle, Integer year, String synopsis) {
		this(null,title,originalTitle, year, null, null, null, synopsis, null, null);
	}
	public Movie(String title, String originalTitle, Integer year, Person director, String synopsis) {
		this(null,title,originalTitle, year, null, director, null, synopsis,null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director) {
		this(null, title, originalTitle, year, duration, director, null, null, null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director, String synopsis) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, null, null);
	}
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director, String synopsis, ColorMode colorMode) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, colorMode, null);
	}

	public Movie(String title, String originalTitle, Integer year, Integer duration, String synopsis, ColorMode colorMode) {
		this(null, title, originalTitle, year, duration, null, null, synopsis, colorMode, null);
	}
	
		
	public Movie(Integer idMovie, String title, String originalTitle, Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, ColorMode colorMode, Float rating) {
		super();
		this.idMovie = idMovie;
		this.title = title;
		this.originalTitle = originalTitle;
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = actors;
		this.synopsis = synopsis;
		ColorMode = colorMode;
		this.rating = rating;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie")
	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer id_movie) {
		this.idMovie = id_movie;
	}

	@Column(nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_director", nullable = true)
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}
	
	@ManyToMany //(fetch = FetchType.EAGER)
	@JoinTable(name="act",
    joinColumns= @JoinColumn(name="id_movie"),
    inverseJoinColumns=@JoinColumn(name="id_actor")
			)
	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}


	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	@Column(columnDefinition="TEXT")
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	 @Enumerated(EnumType.ORDINAL)
	public ColorMode getColorMode() {
		return ColorMode;
	}

	public void setColorMode(ColorMode colorMode) {
		ColorMode = colorMode;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		return builder.append("[").append(year).append("] \n")
				.append(originalTitle).append("(original title)[").append("#").append(idMovie).toString();
	}
	
}
