package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import cinema.data.Movie;
import cinema.data.Person;

class TestJdbc {

	static DataSource ds; 
	
	@BeforeAll
	static void initDataSource() {
		PGSimpleDataSource pgds = new PGSimpleDataSource();
//		String host = "10.31.1.59";
//		String user = "cinema";
//		String password = "password";
//		String dbname = "postgres";
//		int port = 5432;
		
		String host = "localhost";
		String user = "cinema";
		String password = "password";
		String dbname = "postgres";
		int port = 5432;
		
		pgds.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + dbname);
		pgds.setUser(user);
        pgds.setPassword(password);
        ds = pgds;
//        System.out.println(pgds.getUrl());
	} 
	
	@Test
	void testRead() throws SQLException {
		SortedSet<Movie> listMovies = new TreeSet<>(
				Comparator.comparing(Movie::getTitle).thenComparing(Movie::getReleaseDate)
				);
		String sql = "select * from film";
		try (
			Connection connect = ds.getConnection();
			Statement request = connect.createStatement(); 
			ResultSet res = request.executeQuery(sql);
		){
			while (res.next()) {
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
//				System.out.println(title + ", " + year + ", " + duration);
				
				Movie movie = new Movie(title, year, duration);
				
				listMovies.add(movie);
				
			}
			System.out.println(listMovies);
//			connect.close();
			for (Movie movie : listMovies) {
				System.out.println(movie + " " + movie.getDuration());
			}
			
			var total = listMovies.stream()
			.mapToInt(Movie::getDuration)
			.sum();
			System.out.println("\ntotal = " + total);
		}
	}
	
	@Test
	void testReadFilteredList() throws SQLException {
		String sql = "select * from film where duree >= ?";
		int durationThreshold = 120;
		SortedSet<Movie> listMovies = new TreeSet<>(
				Comparator.comparing(Movie::getTitle).thenComparing(Movie::getReleaseDate)
				);
		
		try(
			Connection connect = ds.getConnection();
			PreparedStatement request = connect.prepareStatement(sql);	
		){
			request.setInt(1, durationThreshold);
			try (ResultSet res = request.executeQuery()){
			
				while (res.next()) {
					String title = res.getString("titre");
					int year = res.getInt("annee");
					int duration = res.getInt("duree");
					
					listMovies.add(new Movie(title, year, duration));
					
				}
			} // res.close()
		} // request/connect.close()
		System.out.println(listMovies);
		
		// check all movies with duration >= durationThreshold
	assertAll(listMovies.stream()
		.map(m -> ()-> assertTrue(m.getDuration() >= durationThreshold, m.getTitle())));
	}
	
	@Test
	void testReadFilteredListTitle() throws SQLException {
		String sql = "select * from film where titre = ?";
		String titleEnter = "Django Unchained";
		SortedSet<Movie> listMovies = new TreeSet<>(
				Comparator.comparing(Movie::getTitle).thenComparing(Movie::getReleaseDate)
				);
		
		try(
				Connection connect = ds.getConnection();
				PreparedStatement request = connect.prepareStatement(sql);	
				){
			request.setString(1, titleEnter);
			try (ResultSet res = request.executeQuery()){
				
				while (res.next()) {
					String title = res.getString("titre");
					int year = res.getInt("annee");
					int duration = res.getInt("duree");
					
					listMovies.add(new Movie(title, year, duration));
					
				}
			} // res.close()
		} // request/connect.close()
		System.out.println(listMovies);
	}
	
	@Test
	void testAddPerson() throws SQLException {
		var persons = List.of(
				new Person("Todd Phillips", LocalDate.of(1930, 5, 31)),								
				new Person("Clint Eastwood", LocalDate.of(1930, 5, 31)),	
				new Person("Bradley Cooper", LocalDate.of(1975, 1, 5))	
		);
		String sql = "insert into individu(prenom, nom, date_naissance) values(?,?,?)";
		try(
			Connection connect = ds.getConnection();
			PreparedStatement request = connect.prepareStatement(sql);
		){
			for (Person person : persons) {
				String fullName = person.getName();
				var parts = fullName.split(" ");
				System.out.println(Arrays.toString(parts));
				request.setString(1, parts[0]);
				request.setString(2, parts[1]);
				request.setDate(3, Date.valueOf(person.getBirthdate()));		
				request.executeUpdate();
			}
		}
		
	}
}
