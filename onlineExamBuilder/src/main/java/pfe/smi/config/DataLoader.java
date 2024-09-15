package pfe.smi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pfe.smi.model.Role;
import pfe.smi.repository.RoleRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si le rôle ROLE_USER existe, sinon le créer
        if (roleRepository.findByName("ROLE_USER") == null) {
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);
        }

        // Vérifier si le rôle ROLE_ADMIN existe, sinon le créer
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleRepository.save(roleAdmin);
        }
    }
}