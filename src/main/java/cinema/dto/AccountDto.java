package cinema.dto;

import java.util.List;

public class AccountDto {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Boolean adminRole;
	private Boolean logged;

	private List<Integer> likedMovies;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(Boolean adminRole) {
		this.adminRole = adminRole;
	}
	
	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	
	public List<Integer> getLikedMovies() {
		return likedMovies;
	}

	public void setLikedMovies(List<Integer> likedMovies) {
		this.likedMovies = likedMovies;
	}
}
