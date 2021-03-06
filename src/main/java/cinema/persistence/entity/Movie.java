package cinema.persistence.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
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

	private List<ColorMode> colorMode;
	private List<Genre> genre;
	private Float rating;
	
	private Set<Role> roles = new HashSet<Role>();
	
	
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
	public Movie(String title, String originalTitle, Integer year, Integer duration, Person director, String synopsis,  List<ColorMode> colorMode) {
		this(null, title, originalTitle, year, duration, director, null, synopsis, colorMode, null);
	}

	public Movie(String title, String originalTitle, Integer year, Integer duration, String synopsis,  List<ColorMode> colorMode) {
		this(null, title, originalTitle, year, duration, null, null, synopsis, colorMode, null);
	}
	
		
	public Movie(Integer idMovie, String title, String originalTitle, Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, List<ColorMode> colorMode, Float rating) {
		this(null, title, originalTitle, year, duration, null, null, synopsis, synopsis, colorMode, null, rating);
	}


	public Movie(Integer idMovie, String title, String originalTitle, Integer year, Integer duration, Person director,
			List<Person> actors, String synopsis, String classification, List<ColorMode> colorMode, List<Genre> genre,
			Float rating) {
		this.idMovie = idMovie;
		this.title = title;
		this.originalTitle = originalTitle;
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = actors;
		this.synopsis = synopsis;
		this.classification = classification;
		this.colorMode = colorMode;
		this.genre = genre;
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
	@JoinTable(
		name="act",
	    joinColumns=@JoinColumn(name="id_movie"),
	    inverseJoinColumns=@JoinColumn(name="id_actor")
			)
	public List<Person> getActors() {
		return actors;
	}

//	@Join
//	public List<Person> getActors() {
//	return actors;
//	}
	
	
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
	
    @ElementCollection(targetClass=ColorMode.class)
    public List<ColorMode> getColorMode() {
		return colorMode;
	}

	public void setColorMode(List<ColorMode> colorMode) {
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
	
	@OneToMany(mappedBy = "pk.movie")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		return builder.append("[").append(year).append("] \n")
				.append(originalTitle).append("(original title)[").append("#").append(idMovie).toString();
	}
	
}
