package cinema.model;

import java.io.Serializable;

public class LoggedUserResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUser;
	private String username;

	public LoggedUserResponse() {}
	
	public LoggedUserResponse(Integer idUser, String username) {
		this.idUser = idUser;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Integer getIdUser() {
		return idUser;
	}
	
}
