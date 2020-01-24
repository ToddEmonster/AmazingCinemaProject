package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class TestDateTime {

	@Test
	void testParseFrenchDate() {
		String dateStr = "15/02/2024";
		LocalDate date = LocalDate.parse(dateStr, 
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(date);
		assertAll(
				()->assertEquals(15, date.getDayOfMonth(), "day"),
				()->assertEquals(01, date.getMonthValue(), "month"),
				()->assertEquals(2020, date.getYear(), "year"));
	}
	
	@Test
	void testFormatDate() {
		LocalDate date = LocalDate.now();
		var formats = List.of(
				DateTimeFormatter.ofPattern("dd/MM/yyyy"),
				DateTimeFormatter.ofPattern("eeee dd/MMMM/yyyy"),
				DateTimeFormatter.ofPattern("EEEE yyyy/MM/dd", Locale.ENGLISH),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("es", "es")),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("lv", "lv")),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("ru", "ru")),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("ja", "ja")),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("hi", "in")),
				DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("en", "in"))
				);
		
				formats.forEach(f-> System.out.println(date.format(f))); 
	
	}
	
	@Test
	void localeIndia() {
		var date = LocalDate.now();
		Arrays.stream(
				Locale.getAvailableLocales())
				.filter(l -> l.getCountry().equals("IN"))
				.map(l -> date.format(
						DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", l)))
				.forEach(System.out::println);
		
//		var format1 = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("kok", "in"));
//		var format2 = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("ks", "in"));
//		System.out.println("\n" + date.format(format1));
//		System.out.println(date.format(format2));
		
	}
	
	@Test
	void aroundTheWorld() {
		
		
		var format1 = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy, kk:mm:ss");
		var places = List.of(
				 LocalDateTime.now(),
				 LocalDateTime.now(ZoneId.of("Pacific/Midway")),
				 LocalDateTime.now(ZoneId.of("Pacific/Auckland"))
				);
		
		places.forEach(p -> System.out.println(format1.format(p)));
//		places.forEach(p -> System.out.println(p.format(format1)));
		
		System.out.println("");
		System.out.println(format1.format(LocalDateTime.now()));
		
	}
}
