package cinema.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
	private String classification;

	private ColorMode colorMode;
	private List<Genre> genre;
	private Float rating;
	
	public Movie() {
		this(null, null);	
	}
	
	
	// Title, year, duration
	public Movie(String title, Integer year, Integer duration) {
		this(null, title, null, year, duration, null, null, null, null, null);
	}
	
	// Title, year, rating
	public Movie(String title, Integer year, Float rating) {
		this(null, title, null, year, null, null, null, null, null, rating);
	}
	
	public Movie(String title, String originalTitle, Integer year, Person director) {
		this(null, title, originalTitle, year, null, director, null, null, null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, String synopsis) {
		this(null, title, originalTitle, year, null, null, null, synopsis, null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Person director, String synopsis) {
		this(null, title, originalTitle, year, null, director, null, synopsis,null, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director, String synopsis) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, null, null);
	}
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director, String synopsis,  ColorMode colorMode) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, colorMode, null);
	}

	public Movie(String title, String originalTitle, Integer year, Integer duration, String synopsis,  ColorMode colorMode) {
		this(null, title, originalTitle, year, duration, null, null, synopsis, colorMode, null);
	}
	
	public Movie(String title, String originalTitle, Integer year, Integer duration,
			List<Person> actors, String synopsis, ColorMode colorMode, Float rating) {
		this(null, title, originalTitle, year, duration, null, null, synopsis, synopsis, colorMode, null, rating);
	}

	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director,
			String synopsis, ColorMode colorMode, Float rating) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, synopsis, colorMode, null, rating);
	}
	
	public Movie(Integer idMovie, String title, String originalTitle, Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, ColorMode colorMode, Float rating) {
		this(null, title, originalTitle, year, duration, director, actors, synopsis, synopsis, colorMode, null, rating);
	}
	
	// Pour testMappingEntities initData
	// Tout sauf idMovie, actors, genre 
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			String synopsis, String classification, 
			ColorMode colorMode,
			Float rating) {
		this(null, title, originalTitle, 
			year, duration, director, 
			null, synopsis, classification, 
			colorMode, null, rating);
	}	
	
	// Tout sauf idMovie, originalTitle, duration, director, actors, synopsis, classification, colorMode, genre, rating
	public Movie(String title, 
			Integer year) {
		this(null, title, null, 
			year, null, null, 
			null, null, null, 
			null, null, null);
	}
	
	// Tout sauf idMovie, duration, director, actors, synopsis, classification, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year) {
		this(null, title, originalTitle, 
			year, null, null, 
			null, null, null, 
			null, null, null);
	}
	
	// Tout sauf idMovie, director, actors, synopsis, classification, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration) {
		this(null, title, originalTitle, 
			year, duration, null, 
			null, null, null, 
			null, null, null);
	}
	
	// Tout sauf idMovie, actors, synopsis, classification, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director) {
		this(null, title, originalTitle, 
			year, duration, director, 
			null, null, null, 
			null, null, null);
	}
	
	// Tout sauf idMovie, synopsis, classification, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, null, null, 
			null, null, null);
	}
	
	// Tout sauf idMovie, classification, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, synopsis, null, 
			null, null, null);
	}	
	
	// Tout sauf idMovie, colorMode, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, synopsis, classification, 
			null, null, null);
	}	
	
	// Tout sauf idMovie, genre, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification, 
			ColorMode colorMode) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, synopsis, classification, 
			colorMode, null, null);
	}	
	
	
	// Tout sauf idMovie, rating
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification, 
			ColorMode colorMode, List<Genre> genre) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, synopsis, classification, 
			colorMode, genre, null);
	}	
	
	// Tout sauf idMovie
	public Movie(String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification, 
			ColorMode colorMode, List<Genre> genre,
			Float rating) {
		this(null, title, originalTitle, 
			year, duration, director, 
			actors, synopsis, classification, 
			colorMode, genre, rating);
	}	
	
	public Movie(Integer idMovie, String title, String originalTitle, 
			Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification, 
			ColorMode colorMode, List<Genre> genre,
			Float rating) {
		this.idMovie = idMovie;
		this.title = title;
		this.originalTitle = originalTitle;
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<>();
		this.synopsis = synopsis;
		this.classification = classification;
		this.colorMode = colorMode;
		this.genre = new ArrayList<>();
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
    inverseJoinColumns=@JoinColumn(name="id_actor"))
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
	
    public ColorMode getColorMode() {
		return colorMode;
	}

	public void setColorMode(ColorMode colorMode) {
		this.colorMode = colorMode;
	}

	 @ElementCollection(targetClass=Genre.class)
	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		return builder.append("[").append(year).append("] \n")
				.append(originalTitle).append("(original title)[").append("#").append(idMovie).toString();
	}
	
}
