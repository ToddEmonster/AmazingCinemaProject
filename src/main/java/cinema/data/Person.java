package cinema.data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;
import java.util.OptionalInt;


public class Person {
	
	private String name;
	private LocalDate birthdate;
	
	public Person(String name) {
		this(name, null);
	}
		
	public Person(String name, LocalDate birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	

	/**
	 * calculate age with LocalDate, MOnthDay and Person birthDate 
	 * @return age of Person
	 */
	public OptionalInt getAge() {
		if (Objects.isNull(birthdate)) {
			return OptionalInt.empty();
	}
		
		LocalDate todayFull = LocalDate.now();
		MonthDay birthDay = MonthDay.of(birthdate.getMonthValue(), birthdate.getDayOfMonth());
		MonthDay today = MonthDay.now();
		int deltaYear = todayFull.getYear() - birthdate.getYear();
		
		if (today.compareTo(birthDay) < 0) {
			deltaYear --;
		}
		return OptionalInt.of(deltaYear);
			
	}
	
	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknown")+ ")";
	}
	
	// TODO : equals + hashCode
	
}
