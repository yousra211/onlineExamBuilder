package pfe.smi.dto;

public class AuthenticationRequest {
	  private String username;
	    private String password;

	    // Constructeur par défaut
	    public AuthenticationRequest() {
	    }

	    // Constructeur avec paramètres
	    public AuthenticationRequest(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    // Getters et setters
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}
