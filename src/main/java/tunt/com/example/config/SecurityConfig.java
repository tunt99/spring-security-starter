package tunt.com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Enable Spring Security's web security support
@EnableMethodSecurity(jsr250Enabled = true) // Enable method-level security with JSR-250 annotations
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure form login with custom login processing URL
        http.formLogin((form) -> form.loginProcessingUrl("/login"));
        http.authorizeHttpRequests(authorize -> authorize
                // Allow unauthenticated access to login and register endpoints
                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register").permitAll()

                // Require admin:read authority for /api/v1/admin/vip
                .requestMatchers("/api/v1/admin/vip").hasAuthority("ADMIN")

                // Require authentication for all other requests
                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("admin", "user") // Automatically adds ROLE_ prefix to roles
                .authorities("admin:read", "admin:write") // If  you use authorities, ROLE_ prefix is not added
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user123"))
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
