package com.poc.passwordresettoken.config.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/user/forgotPassword").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/resetPassword").permitAll()
                .anyRequest().authenticated()//.permitAll()
                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
