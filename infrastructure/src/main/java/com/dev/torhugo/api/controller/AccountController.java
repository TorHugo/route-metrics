package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.models.admin.AccountAdminDTO;
import com.dev.torhugo.api.models.admin.InativateAccountDTO;
import com.dev.torhugo.api.models.admin.UpdateAccountDTO;
import com.dev.torhugo.api.models.request.CreateAccountDTO;
import com.dev.torhugo.models.UcAccountAdminDTO;
import com.dev.torhugo.models.UcInativateAccountDTO;
import com.dev.torhugo.models.UcUpdateAccountDTO;
import com.dev.torhugo.usecase.*;
import com.dev.torhugo.api.AccountAPI;
import com.dev.torhugo.api.mappers.AccountMapper;
import com.dev.torhugo.api.models.response.AccountCreateDTO;
import com.dev.torhugo.api.models.response.BasicAccountDTO;
import com.dev.torhugo.models.UcAccountDTO;
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
    public AccountCreateDTO createAccount(final AccountAdminDTO request) {
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
