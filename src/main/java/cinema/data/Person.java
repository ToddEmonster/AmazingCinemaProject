package cinema.data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;
import java.util.OptionalInt;

public class Person {
	private String name;
	private LocalDate birthDate;
	
	
	public Person(String name, LocalDate birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public Person(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * calculate age with LocalDate, MOnthDay and Person birthDate 
	 * @return age of Person
	 */
	public OptionalInt getAge() {
		if (Objects.isNull(birthDate)) {
			return OptionalInt.empty();
	}
		
		LocalDate todayFull = LocalDate.now();
		MonthDay birthDay = MonthDay.of(birthDate.getMonthValue(), birthDate.getDayOfMonth());
		MonthDay today = MonthDay.now();
		int deltaYear = todayFull.getYear() - birthDate.getYear();
		
		if (today.compareTo(birthDay) < 0) {
			deltaYear --;
		}
		return OptionalInt.of(deltaYear);
			
	}
	
	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthDate, "unknown")+ ")";
	}
	
	// TODO : equals + hashCode
	
}
