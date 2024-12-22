package org.esteban.lescano.dmhouse.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService {
    private final JdbcTemplate jdbcTemplate;

    public ClientDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDetails loadUserByUsername(String username) {
        String queryUser = "SELECT email, password FROM public.client WHERE email = ?";
        return jdbcTemplate.queryForObject(queryUser, new Object[]{username}, (rs, rowNum) -> {
            String email = rs.getString("email");
            String password = rs.getString("password");

            return User.builder()
                    .username(email)
                    .password(password)
                    .roles("USER") // Personalizar según necesidad
                    .build();
        });
    }
}