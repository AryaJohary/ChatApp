package com.example.demo2.configs;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain handleSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/index.html", "/ws/**").permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form.defaultSuccessUrl("/index.html", true))
                .logout(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        User user1 = new User("arya1", "{noop}arya1", Collections.emptyList());
        User user2 = new User("arya2", "{noop}arya2", Collections.emptyList());
        return new InMemoryUserDetailsManager(user1, user2);
    }

}
