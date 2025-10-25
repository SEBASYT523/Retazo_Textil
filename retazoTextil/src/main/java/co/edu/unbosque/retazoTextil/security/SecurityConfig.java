package co.edu.unbosque.retazoTextil.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**", 
                    "/v3/api-docs/**",
                    "/api-docs/**",
                    "/swagger-ui.html",
                    "/public/**"
                ).permitAll() // 🔓 Swagger y endpoints públicos
                .anyRequest().permitAll() // 🔓 Permite todo temporalmente
            )
            .formLogin(login -> login.disable()) // ❌ Quita el login por formulario
            .httpBasic(basic -> basic.disable()); // ❌ Quita autenticación básica
        return http.build();
    }
}
