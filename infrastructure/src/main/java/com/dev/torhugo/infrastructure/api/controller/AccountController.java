package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.infrastructure.api.models.admin.AccountAdminDTO;
import com.dev.torhugo.infrastructure.api.models.admin.InativateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.admin.UpdateAccountDTO;
import com.dev.torhugo.infrastructure.api.models.request.CreateAccountDTO;
import com.dev.torhugo.application.dto.UcAccountAdminDTO;
import com.dev.torhugo.application.dto.UcInativateAccountDTO;
import com.dev.torhugo.application.dto.UcUpdateAccountDTO;
import com.dev.torhugo.application.usecase.*;
import com.dev.torhugo.infrastructure.api.AccountAPI;
import com.dev.torhugo.infrastructure.api.mappers.AccountMapper;
import com.dev.torhugo.infrastructure.api.models.response.AccountCreateDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicAccountDTO;
import com.dev.torhugo.application.dto.UcAccountDTO;
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
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountCreateDTO createAdminAccount(final AccountAdminDTO request) {
        final var input = new UcAccountAdminDTO(request.name(), request.email(), passwordEncoder.encode(request.password()), request.active(), request.admin());
        return new AccountCreateDTO(createAccountAdminUseCase.execute(input));
    }

    @Override
    public List<BasicAccountDTO> findAllAccounts() {
        return accountMapper.mapperToBasicListAccount(findAllAccountsUseCase.execute());
    }

    @Override
    public void inativateAccount(final InativateAccountDTO request) {
        final var input = new UcInativateAccountDTO(request.accouts());
        inativateAccountUseCase.execute(input);
    }

    @Override
    public BasicAccountDTO updateAccount(final UpdateAccountDTO request,
                                         final UUID accountId) {
        final var input = new UcUpdateAccountDTO(accountId, request.name(), request.email(), passwordEncoder.encode(request.password()), request.active(), request.admin());
        return accountMapper.mapperToBasicAccount(updateAccountUseCase.execute(input));
    }

    @Override
    public AccountCreateDTO createAccount(final CreateAccountDTO request) {
        final var input = new UcAccountDTO(request.name(), request.email(), passwordEncoder.encode(request.password()));
        return new AccountCreateDTO(createAccountUseCase.execute(input));
    }

    @Override
    public BasicAccountDTO findAccount(final Principal request) {
        return accountMapper.mapperToBasicAccount(findAccountUseCase.execute(request.getName()));
    }
}
