package pfe.smi.controller;

import pfe.smi.dto.AuthenticationRequest;
import pfe.smi.dto.AuthenticationResponse;
import pfe.smi.dto.UserRegistrationDto;
import pfe.smi.model.User;
import pfe.smi.security.JwtTokenUtil;
import pfe.smi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")  // Permet les requêtes CORS depuis Angular
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Endpoint pour l'inscription
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            User newUser = userService.registerNewUser(registrationDto);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint pour l'authentification et la génération de token JWT
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {

        // Authentifier l'utilisateur
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Charger les détails de l'utilisateur
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        // Générer le token JWT
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        // Retourner la réponse avec le token
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}