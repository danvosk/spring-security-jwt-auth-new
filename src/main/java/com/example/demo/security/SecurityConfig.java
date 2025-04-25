package com.example.demo.security;

import com.example.demo.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authManager = authenticationManager(http);

        JwtAuthenticationFilter authFilter =
                new JwtAuthenticationFilter(jwtUtil, authManager);
        authFilter.setFilterProcessesUrl("/auth/login");

        JwtAuthorizationFilter authorizationFilter =
                new JwtAuthorizationFilter(jwtUtil, userDetailsService);

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
    .requestMatchers("/auth/**", "/h2-console/**").permitAll()

    
    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "TRAINER", "ADMIN")

    
    .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("TRAINER", "ADMIN")

    
    .requestMatchers("/api/**").hasRole("ADMIN")

    // diğer her şeyi engelle
    .anyRequest().authenticated()
)
                .authenticationManager(authManager)
                .addFilter(authFilter)
                .addFilterAfter(authorizationFilter, JwtAuthenticationFilter.class)
                .headers(headers -> headers
                        .frameOptions(frameOpts -> frameOpts.disable())
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}