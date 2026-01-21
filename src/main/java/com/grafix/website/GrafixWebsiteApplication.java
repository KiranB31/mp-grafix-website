package com.grafix.website;

import com.grafix.website.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GrafixWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrafixWebsiteApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAdmin(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (adminRepository.findByUsername("admin").isEmpty()) {
				UserAdmin admin = new UserAdmin();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setEmail("admin@grafix.com");
				admin.setFullName("System Admin");
				admin.setRole("ADMIN");
				adminRepository.save(admin);
				System.out.println(">>> Created default admin account (user: admin, pass: admin123)");
			} else {
				UserAdmin admin = adminRepository.findByUsername("admin").get();
				admin.setPassword(passwordEncoder.encode("admin123"));
				adminRepository.save(admin);
				System.out.println(">>> Updated admin password to: admin123");
			}
		};
	}
}
