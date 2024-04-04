package com.dev.torhugo.security.service;

import com.dev.torhugo.api.models.request.LoginRequest;
import com.dev.torhugo.security.jwt.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public ResponseCookie login(final LoginRequest request){
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return tokenUtils.generateToken(userDetails);
    }
}
