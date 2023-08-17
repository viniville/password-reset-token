package com.poc.passwordresettoken.config.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(amrmr -> amrmr
                        .requestMatchers(
                                new AntPathRequestMatcher("/user/forgotPassword", HttpMethod.POST.name(), false),
                                new AntPathRequestMatcher("/user/resetPassword", HttpMethod.POST.name(), false),
                                new AntPathRequestMatcher("/swagger-ui.html", HttpMethod.GET.name(), false),
                                new AntPathRequestMatcher("/swagger-ui/*", HttpMethod.GET.name(), false),
                                new AntPathRequestMatcher("/v3/api-docs/swagger-config", HttpMethod.GET.name(), false),
                                new AntPathRequestMatcher("/v3/api-docs.yaml", HttpMethod.GET.name(), false),
                                new AntPathRequestMatcher("/v3/api-docs", HttpMethod.GET.name(), false)
                        ).permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
