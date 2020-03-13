package cinema.persistence.entity;

import java.time.LocalDate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "accounts")
public class Account {
	
	private Integer idAccount;
	private String firstName;
	private String lastName;
//	private LocalDate birthdate;
	private String username;
	private String email;
	private String password;
	private Boolean isAdmin;
	private Boolean logged;
	
	
	private Set<LikedMovies> likedMovies;
	
	
	public Account() {
		this(null, null, null, null, null, false, false);
	}
	
	
	// adminRole set as null
	public Account(String firstName, String lastName, String username, 
			   String email, String password, Boolean logged) {
	this(null, firstName, lastName, username, email, password, false, false);
	}
	
	// adminRole defined
	public Account(String firstName, String lastName, String username, 
			   String email, String password, Boolean isAdmin, Boolean logged) {
	this(null, firstName, lastName, username, email, password, isAdmin, false);
	}
	
	public Account(Integer idAccount, String firstName, String lastName, String username, 
				   String email, String password, Boolean isAdmin, Boolean logged) {
		super();
		this.idAccount = idAccount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.logged = false;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	public Integer getidAccount() {
		return idAccount;
	}

	public void setidAccount(Integer id_user) {
		this.idAccount = id_user;
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	
	
	@OneToMany(mappedBy="account")
	public Set<LikedMovies> getLikedMovies() {
		return likedMovies;
	}

	public void setLikedMovies(Set<LikedMovies> likedMovies) {
		this.likedMovies = likedMovies;
	}

	
}
