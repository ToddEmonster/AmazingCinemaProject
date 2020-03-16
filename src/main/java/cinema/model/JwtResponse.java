package cinema.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jwttoken;
	private String username;
	private Integer idUser;
	private Boolean isAdmin;
	
	public JwtResponse(String jwttoken, String username, Integer idUser, Boolean isAdmin) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.idUser = idUser;
		this.isAdmin = isAdmin;
	}
	
	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return this.username;
	}
	
	public Integer getIdUser() {
		return this.idUser;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}
	
}
