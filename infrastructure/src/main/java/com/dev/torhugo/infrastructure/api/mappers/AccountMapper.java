package com.dev.torhugo.infrastructure.api.mappers;

import com.dev.torhugo.application.dto.*;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.infrastructure.api.models.admin.AccountAdminDTO;
import com.dev.torhugo.infrastructure.api.models.admin.InativateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.admin.UpdateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.request.CreateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.request.UpdatePasswordDTO;
import com.dev.torhugo.infrastructure.api.models.response.AccountCreateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AccountMapper {

    public UcAccountAdminDTO mapperToUseCase(final AccountAdminDTO request){
        return new UcAccountAdminDTO(
                request.name(),
                request.email(),
                request.password(),
                request.active(),
                request.admin()
        );
    }

    public AccountCreateDTO mapperFromUseCase(final UUID accountId){
        return new AccountCreateDTO(accountId);
    }

    public BasicAccountDTO mapperFromUseCase(final Account account) {
        return BasicAccountDTO.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .email(account.getEmail())
                .active(account.isActive())
                .admin(account.isAdmin())
                .lastAccess(account.getLastAccess())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    public List<BasicAccountDTO> mapperFromUseCase(final List<UcBasicAccountDTO> accounts) {
        return accounts.stream().map(account ->
                BasicAccountDTO.builder()
                    .accountId(account.accountId())
                    .name(account.name())
                    .email(account.email())
                    .active(account.active())
                    .admin(account.admin())
                    .lastAccess(account.lastAccess())
                    .createdAt(account.createdAt())
                    .updatedAt(account.updatedAt())
                    .build()
            ).toList();
    }

    public UcInativateAccountDTO mapperToUseCase(final InativateAccountDTO request){
        return new UcInativateAccountDTO(
                request.accouts()
        );
    }

    public UcUpdateAccountDTO mapperToUseCase(final UUID accountId,
                                              final UpdateAccountDTO request,
                                              final String passwordEncoded){
        return new UcUpdateAccountDTO(
                accountId,
                request.name(),
                request.email(),
                passwordEncoded,
                request.active(),
                request.admin()
        );
    }

    public UcAccountDTO mapperToUseCase(final CreateAccountDTO request,
                                        final String passwordEncoded){
        return new UcAccountDTO(
                request.name(),
                request.email(),
                passwordEncoded
        );
    }

    public UcUpdatePasswordDTO mapperToUseCase(final String newPasswordEncoded,
                                               final boolean comparingPasswords){
        return new UcUpdatePasswordDTO(
                newPasswordEncoded,
                comparingPasswords
        );
    }
}
