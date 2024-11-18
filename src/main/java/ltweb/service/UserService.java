package ltweb.service;

import ltweb.model.Role;
import ltweb.model.Users;
import ltweb.repository.UserRepository;
import ltweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users registerNewUser(Users user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public Users getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
