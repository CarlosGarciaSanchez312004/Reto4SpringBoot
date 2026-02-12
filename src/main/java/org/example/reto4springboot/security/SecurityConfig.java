package org.example.reto4springboot.security;


import jakarta.servlet.http.HttpServletResponse;
import org.example.reto4springboot.dto.ErrorResponseDTO;
import tools.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permitir ver museos a todo el mundo
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        // Para crear o borrar, hay que estar autenticado
                        .requestMatchers(HttpMethod.POST, "/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                        .anyRequest().permitAll()
                ).httpBasic(basic -> {
                    basic.authenticationEntryPoint(customAuthenticationEntryPoint());
                });
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorResponseDTO error = new ErrorResponseDTO(
                    "No autorizado",
                    "Debes iniciar sesión para realizar cambios.",
                    401
            );
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(error));
        };
    }
}
