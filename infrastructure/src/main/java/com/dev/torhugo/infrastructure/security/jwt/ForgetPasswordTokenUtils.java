package com.dev.torhugo.infrastructure.security.jwt;

import com.dev.torhugo.domain.entity.Account;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static io.jsonwebtoken.Jwts.builder;
import static org.springframework.http.HttpHeaders.COOKIE;
import static org.springframework.http.ResponseCookie.from;

@Component
@Slf4j
public class ForgetPasswordTokenUtils {
    private static final String COOKIE = "token";
    @Value("${api.app.secret}")
    private String secret;
    @Value("${api.jwt.reset-password}")
    private Integer expiration;

    public ResponseCookie generateForgetPasswordToken(final Account account) {
        final var token = generateTokenFromUser(account);
        log.info("token: {}.", token);
        return from(COOKIE, token)
                .path("/")
                .maxAge(216_000)
                .secure(true)
                .httpOnly(true)
                .build();
    }

    private String generateTokenFromUser(final Account account) {
        if (!account.isActive())
            throw new AccessDeniedException("Account Expired!");
        final var dateNow = new Date();

        return builder()
                .claim("account_id", account.getAccountId())
                .claim("name", account.getName())
                .claim("email", account.getEmail())
                .subject(account.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(dateNow.getTime() + expiration))
                .signWith(getSigningKey(secret))
                .compact();
    }

    private SecretKey getSigningKey(final String secret){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
