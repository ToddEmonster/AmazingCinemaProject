package cinema.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "accounts")
public class Account {
	
	private Integer idUser;
	private String firstName;
	private String lastName;
//	private LocalDate birthdate;
	private String username;
	private String email;
	private String password;
	private Boolean adminRole;
	private Boolean logged;
	
	
	private List<Integer> likedMovies;
	
	
	public Account() {
		this(null, null, null, null, null, null, false);
	}
	
	
	// adminRole not mandatory
	public Account(String firstName, String lastName, String username, 
			   String email, String password, Boolean logged) {
	this(null, firstName, lastName, username, email, password, null, false);
	}
	
	// adminRole not mandatory
	public Account(String firstName, String lastName, String username, 
			   String email, String password, Boolean adminRole, Boolean logged) {
	this(null, firstName, lastName, username, email, password, adminRole, false);
	}
	
	public Account(Integer idUser, String firstName, String lastName, String username, 
				   String email, String password, Boolean adminRole, Boolean logged) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.adminRole = adminRole;
		this.logged = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer id_user) {
		this.idUser = id_user;
	}
	
	@NotEmpty
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotEmpty
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotEmpty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotEmpty
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty
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
