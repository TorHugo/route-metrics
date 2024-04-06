package com.dev.torhugo.infrastructure.security.service;

import com.dev.torhugo.infrastructure.api.models.request.LoginDTO;
import com.dev.torhugo.infrastructure.security.jwt.TokenUtils;
import com.dev.torhugo.application.usecase.UpdateLastAccessUseCase;
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
    private final UpdateLastAccessUseCase updateLastAccessUseCase;
    private final TokenUtils tokenUtils;

    public ResponseCookie login(final LoginDTO request){
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final var cookie = tokenUtils.generateToken(userDetails);
        updateLastAccessUseCase.execute(request.username());
        return cookie;
    }
}
