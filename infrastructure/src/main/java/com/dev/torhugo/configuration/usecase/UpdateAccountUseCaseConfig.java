package com.dev.torhugo.configuration.usecase;

import com.dev.torhugo.mapper.AccountMapper;
import com.dev.torhugo.repository.AccountRepository;
import com.dev.torhugo.usecase.FindAccountUseCase;
import com.dev.torhugo.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UpdateAccountUseCaseConfig {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Bean
    public UpdateAccountUseCase updateAccountUseCase(){
        return new UpdateAccountUseCase(accountRepository, accountMapper);
    }
}
