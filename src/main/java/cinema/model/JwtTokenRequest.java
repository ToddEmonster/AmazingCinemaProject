package cinema.model;

import java.io.Serializable;

public class JwtTokenRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String token;
	
	public JwtTokenRequest() {}

	public JwtTokenRequest(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	};
	
	
	
}
