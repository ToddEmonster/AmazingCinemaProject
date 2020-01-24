package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class TestMap {

	@Test
	void testMap() {
		Map<Integer, Integer> nbMoviesByYear = new TreeMap<>();
		nbMoviesByYear.put(2019, 22);
		nbMoviesByYear.put(2014, 14);
		nbMoviesByYear.put(2020, 30);
		nbMoviesByYear.put(2010, 10);
		System.out.println(nbMoviesByYear);
		nbMoviesByYear.put(2020, 33);
		System.out.println("en 2020: " + nbMoviesByYear.get(2020));
		for (var year : nbMoviesByYear.keySet()) {
			System.out.println(year);
		}
		for (var year : nbMoviesByYear.values()) {
			System.out.println(year);
		}
		
	}

}
