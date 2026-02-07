package tunt.com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // Enable Spring Security's web security support
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User
                .withUsername("admin")
                .password("admin123")
                .roles("admin", "user")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("{noop}user123")
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
