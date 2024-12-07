package org.esteban.lescano.dmhouse.configuration;

import org.esteban.lescano.dmhouse.security.JwtAuthConverter;
import org.esteban.lescano.dmhouse.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };

    private final JwtAuthConverter jwtAuthConverter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter, CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthConverter = jwtAuthConverter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers(SWAGGER_WHITELIST))
               .cors(cors -> cors.configurationSource(corsConfigurationSource()))
               .authorizeHttpRequests(auth -> auth
                    .requestMatchers(SWAGGER_WHITELIST).permitAll() // Permitir acceso a Swagger
                    .requestMatchers(HttpMethod.GET, "/wallet/**").permitAll() // Rutas públicas
                    .requestMatchers("/admin/**").hasRole(ROLE_ADMIN) // Solo admin
                    .requestMatchers("/").hasRole(ROLE_USER) // Solo usuarios autenticados
                    .anyRequest().authenticated())
               .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
               .userDetailsService(customUserDetailsService) // Usamos el CustomUserDetailsService aquí
               .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Dominio del frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


