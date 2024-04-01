package com.dev.torhugo;

import com.dev.torhugo.config.DefaultUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import com.dev.torhugo.messaging.Queue;
import com.dev.torhugo.messaging.QueueProducer;
import com.dev.torhugo.models.AccountInput;
import com.dev.torhugo.repository.AccountRepository;

import java.util.Objects;
import java.util.UUID;

public class CreateAccountUseCase extends DefaultUseCase {
    private final AccountRepository accountRepository;
    private final QueueProducer queueProducer;
    public CreateAccountUseCase(final AccountRepository accountRepository,
                                final QueueProducer queueProducer) {
        super();
        this.accountRepository = accountRepository;
        this.queueProducer = queueProducer;
    }
    public UUID execute(final AccountInput input){
        logger.info("Executing CreateAccountUseCase.");
        final var queue = Queue.WELCOME.getName();
        final var existisAccount = this.accountRepository.findByEmail(input.email());
        if(Objects.nonNull(existisAccount))
            throw new InvalidArgumentError("Account already exists!");
        final var actualAccount = Account.create(
                input.name(),
                input.email(),
                input.password()
        );
        this.accountRepository.save(actualAccount);
        this.queueProducer.sendMessage(queue, input.email());
        return actualAccount.getAccountId();
    }
}
