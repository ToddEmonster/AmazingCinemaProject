package cinema.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Movie {
	// attribute
	private String title;
	private int releaseDate;
	private int duration;
	private Person director;
	private List<Person> actors;
	 
	public Movie() {
		
	}
	
	public Movie(String title, int releaseDate) {
		this(title, releaseDate, 0);
		
	}
	
	public Movie(String title, int releaseDate, int duration) {
		this(title, releaseDate, duration, null);
	}
	
	public Movie(String title, int releaseDate, Person director) {
		this(title, releaseDate, 0, director);
	}
	

	public Movie(String title, int releaseDate, int duration, Person director) {
		super();
		this.title = Objects.requireNonNull(title);
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = Objects.requireNonNull(title);
	}


	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}
	
	
	///list persons methods ///
	public void addActor(Person actor) {
		this.actors.add(actor);
	}

	public void addAllActors(Collection<? extends Person> actors) {
		this.actors.addAll(actors);
	}
	
	public void addAllActors(Person... actors) {
		this.addAllActors(Arrays.asList(actors));
	}
	
	public Iterator<Person> iteratorActor(){
		return this.actors.iterator();
	}
	
	public Stream<Person> streamActors(){
		return this.actors.stream();
	}
	

	@Override
	public String toString() {
		return  title + " (" + releaseDate + (duration == 0 ? "" : ", " + duration + "mn") + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, releaseDate);
	}

	@Override
	public boolean equals(Object obj) {	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Movie other = (Movie) obj;

		return this.title.equals(other.title)
				&& this.releaseDate == other.releaseDate;
		
	}


	
}



 
