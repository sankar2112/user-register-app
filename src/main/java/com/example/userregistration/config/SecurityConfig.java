package com.example.userregistration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests()  // Updated for Spring Security 6
                .requestMatchers("/api/users/register", "/api/users/validate", "/api/users/login").permitAll()  // Allow unauthenticated access
                .anyRequest().authenticated()  // Secure other endpoints
                .and()
                .httpBasic();  // Enable Basic Authentication
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}


//package com.example.userregistration.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable CSRF for simplicity (not recommended for production)
//                .authorizeRequests()
//                .antMatchers("/api/users/register", "/api/users/validate", "/api/users/login").permitAll() // Allow unauthenticated access
//                .anyRequest().authenticated() // Secure other endpoints
//                .and()
//                .httpBasic(); // Enable Basic Authentication
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }
//}
