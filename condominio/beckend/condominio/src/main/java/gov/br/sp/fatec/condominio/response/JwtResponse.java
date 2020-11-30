package gov.br.sp.fatec.condominio.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String nome;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.nome = username;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return nome;
	}

	public void setUsername(String username) {
		this.nome = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
