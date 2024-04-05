package com.dev.torhugo.api;

import com.dev.torhugo.api.models.admin.AccountAdminDTO;
import com.dev.torhugo.api.models.admin.InativateAccountDTO;
import com.dev.torhugo.api.models.admin.UpdateAccountDTO;
import com.dev.torhugo.api.models.request.CreateAccountDTO;
import com.dev.torhugo.api.models.response.AccountCreateDTO;
import com.dev.torhugo.api.models.response.BasicAccountDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/account")
public interface AccountAPI {
    @PostMapping("/admin/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    AccountCreateDTO createAccount(final @Valid @RequestBody AccountAdminDTO request);
    @GetMapping("/admin/find-all")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    List<BasicAccountDTO> findAllAccounts();
    @PutMapping("/admin/inativate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    void inativateAccount(final @Valid @RequestBody InativateAccountDTO request);
    @PutMapping("/admin/update/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    BasicAccountDTO updateAccount(final @Valid @RequestBody UpdateAccountDTO request,
                                  final @PathVariable("accountId") UUID accountId);
    @PostMapping("/public/create")
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreateDTO createAccount(final @Valid @RequestBody CreateAccountDTO request);
    @GetMapping("/public/find")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    BasicAccountDTO findAccount(final Principal request);

}
