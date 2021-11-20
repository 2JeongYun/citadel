package com.neukrang.citadel.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class ApiKeyAuthManager implements AuthenticationManager {

    private final DataSource dataSource;
    private final HashSet<String> keySet = new HashSet<>();

    public ApiKeyAuthManager(DataSource dataSource) {
        this.dataSource = dataSource;
        load(keySet);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apikey = (String) authentication.getPrincipal();

        if (!keySet.contains(apikey)) {
            throw new BadCredentialsException("유효하지 않은 API KEY 입니다.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }

    private void load(HashSet<String> keySet) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT KEY FROM API_KEY")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        keySet.add(rs.getString("KEY"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
