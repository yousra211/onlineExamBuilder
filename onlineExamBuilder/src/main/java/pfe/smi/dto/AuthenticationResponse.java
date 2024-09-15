package pfe.smi.dto;

public class AuthenticationResponse {
	private String jwt;

    // Constructeur par défaut
    public AuthenticationResponse() {
    }

    // Constructeur avec paramètre
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter pour le token JWT
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}