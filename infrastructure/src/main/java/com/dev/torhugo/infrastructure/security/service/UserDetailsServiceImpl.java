package com.dev.torhugo.infrastructure.security.service;

import com.dev.torhugo.application.usecase.FindAccountByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;

    @Override
    public UserDetails loadUserByUsername(final String email){
        final var account = findAccountByEmailUseCase.execute(email);
        final var authorityAdminList = createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        final var authorityUserList = createAuthorityList("ROLE_USER");
        final var authority = account.isAdmin() ? authorityAdminList : authorityUserList;

        return UserDetailsImpl.build(account, authority);
    }
}
