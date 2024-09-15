package pfe.smi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfe.smi.dto.UserRegistrationDto;
import pfe.smi.model.Role;
import pfe.smi.model.User;
import pfe.smi.repository.RoleRepository;
import pfe.smi.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(User user) {
        // Encodage du mot de passe avant l'enregistrement
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

  
 // Méthode pour créer un nouvel utilisateur
    public User registerNewUser(UserRegistrationDto registrationDto) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByUsername(registrationDto.getUsername()) != null) {
            throw new RuntimeException("Cet utilisateur existe déjà !");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // Assigner le rôle par défaut "ROLE_USER" lors de l'inscription
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rôle 'ROLE_USER' non trouvé"));
        
        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        return userRepository.save(user);
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Rechercher l'utilisateur dans la base de données
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }

        // Retourner un objet UserDetails (implémentation de Spring Security)
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}