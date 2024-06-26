package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.application.dto.*;
import com.dev.torhugo.infrastructure.api.models.admin.AccountAdminDTO;
import com.dev.torhugo.infrastructure.api.models.admin.InativateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.admin.UpdateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.request.CreateAccountDTO;
import com.dev.torhugo.application.usecase.*;
import com.dev.torhugo.infrastructure.api.AccountAPI;
import com.dev.torhugo.infrastructure.api.mappers.AccountMapper;
import com.dev.torhugo.infrastructure.api.models.request.UpdatePasswordDTO;
import com.dev.torhugo.infrastructure.api.models.response.AccountCreateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateAccountAdminUseCase createAccountAdminUseCase;
    private final FindAllAccountsUseCase findAllAccountsUseCase;
    private final FindAccountUseCase findAccountUseCase;
    private final InativateAccountUseCase inativateAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountCreateDTO createAdminAccount(final AccountAdminDTO request) {
        return accountMapper.mapperFromUseCase(createAccountAdminUseCase.execute(accountMapper.mapperToUseCase(request)));
    }

    @Override
    public List<BasicAccountDTO> findAllAccounts() {
        return accountMapper.mapperFromUseCase(findAllAccountsUseCase.execute());
    }

    @Override
    public void inativateAccount(final InativateAccountDTO request) {
        inativateAccountUseCase.execute(accountMapper.mapperToUseCase(request));
    }

    @Override
    public BasicAccountDTO updateAccount(final UpdateAccountDTO request,
                                         final UUID accountId) {
        final var passwordEncoded = passwordEncoder.encode(request.password());
        return accountMapper.mapperFromUseCase(updateAccountUseCase.execute(accountMapper.mapperToUseCase(accountId, request, passwordEncoded)));
    }

    @Override
    public AccountCreateDTO createAccount(final CreateAccountDTO request) {
        final var passwordEncoded = passwordEncoder.encode(request.password());
        return new AccountCreateDTO(createAccountUseCase.execute(accountMapper.mapperToUseCase(request, passwordEncoded)));
    }

    @Override
    public BasicAccountDTO findAccount(final Principal request) {
        return accountMapper.mapperFromUseCase(findAccountUseCase.execute(request.getName()));
    }

    @Override
    public void updatePassword(final Principal principal,
                               final UpdatePasswordDTO request) {
        final var actualAccount = findAccountUseCase.execute(principal.getName());
        final var newPasswordEncrypt = passwordEncoder.encode(request.newPassword());
        final boolean comparingPasswords = passwordEncoder.matches(request.oldPassword(), actualAccount.getPassword());
        updatePasswordUseCase.execute(actualAccount, accountMapper.mapperToUseCase(newPasswordEncrypt, comparingPasswords));
    }
}
