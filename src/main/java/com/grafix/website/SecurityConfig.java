package com.grafix.website;

import com.grafix.website.repository.AdminRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final AdminRepository adminRepository;

        public SecurityConfig(AdminRepository adminRepository) {
                this.adminRepository = adminRepository;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers("/", "/products", "/reviews/add", "/blog", "/blog/**",
                                                                "/about",
                                                                "/contact", "/css/**", "/images/**", "/uploads/**",
                                                                "/subscribe")
                                                .permitAll()
                                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "STAFF")
                                                .anyRequest().authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/admin/dashboard", true)
                                                .permitAll())
                                .logout((logout) -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/")
                                                .permitAll())
                                .csrf(csrf -> csrf.disable());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return username -> {
                        System.out.println("Login attempt for username: " + username);
                        return adminRepository.findByUsername(username)
                                        .map(admin -> {
                                                System.out.println("User found in DB: " + admin.getUsername()
                                                                + " with role: " + admin.getRole());
                                                return User.withUsername(admin.getUsername())
                                                                .password(admin.getPassword())
                                                                .authorities(admin.getRole() != null ? admin.getRole()
                                                                                : "ADMIN")
                                                                .build();
                                        })
                                        .orElseThrow(() -> {
                                                System.out.println("User NOT found in DB: " + username);
                                                return new UsernameNotFoundException("User not found: " + username);
                                        });
                };
        }
}
