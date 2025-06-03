package co.in.sagarkale.methodsecurity.service;

import co.in.sagarkale.methodsecurity.entity.Role;
import co.in.sagarkale.methodsecurity.entity.Users;
import co.in.sagarkale.methodsecurity.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {
    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin1234")); // Securely store password
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println("Default admin user created!");
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            Users admin = new Users();
            admin.setUsername("user");
            admin.setPassword(passwordEncoder.encode("user1234")); // Securely store password
            admin.setRole(Role.USER);

            userRepository.save(admin);
            System.out.println("Default admin user created!");
        }
    }

}