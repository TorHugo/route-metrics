package com.dev.torhugo.security.service;

import com.dev.torhugo.domain.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private UUID id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private boolean active;
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public static UserDetailsImpl build(final Account account,
                                        final List<GrantedAuthority> authorityList) {
        return new UserDetailsImpl(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.getPassword(),
                account.isActive(),
                authorityList
        );
    }
}
