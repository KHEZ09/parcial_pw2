package com.example.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final RoleSuccessHandler roleSuccessHandler;

    public SecurityConfig(RoleSuccessHandler roleSuccessHandler) {
        this.roleSuccessHandler = roleSuccessHandler;
    }

    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login.html", "/css/**", "/js/**", "/error", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/api/medico/**").hasRole("MEDICO")
                .requestMatchers("/api/paciente/**").hasRole("PACIENTE")
                .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login.html")
                .successHandler(roleSuccessHandler)
                .failureUrl("/login.html?error")
                .permitAll())
            .httpBasic(basic -> {})
            .exceptionHandling(ex -> ex.accessDeniedHandler((request, response, exception) -> response.sendRedirect("/access-denied.html")))
            .logout(logout -> logout.logoutSuccessUrl("/login.html?logout"));
        return http.build();
    }
}
