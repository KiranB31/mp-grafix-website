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
                return username -> adminRepository.findByUsername(username)
                                .map(admin -> new User(
                                                admin.getUsername(),
                                                admin.getPassword(),
                                                Collections.singletonList(new SimpleGrantedAuthority(
                                                                admin.getRole() != null ? admin.getRole() : "ADMIN"))))
                                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        }
}
