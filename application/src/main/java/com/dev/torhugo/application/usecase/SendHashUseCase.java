package com.dev.torhugo.application.usecase;

import com.dev.torhugo.application.config.DefaultUseCase;
import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.domain.entity.ForgetPassword;

import static com.dev.torhugo.application.mappers.ForgetPasswordMapper.mappingToMail;

public class SendHashUseCase extends DefaultUseCase {
    private static final String QUEUE_FORGET_PASSWORD = "QUEUE_EMAIL_FORGET_PASSWORD";
    private final AccountRepository accountRepository;
    private final ForgetPasswordRepository forgetPasswordRepository;
    private final QueueProducer queueProducer;

    public SendHashUseCase(final AccountRepository accountRepository,
                           final ForgetPasswordRepository forgetPasswordRepository,
                           final QueueProducer queueProducer) {
        this.accountRepository = accountRepository;
        this.forgetPasswordRepository = forgetPasswordRepository;
        this.queueProducer = queueProducer;
    }

    public void execute(final String email){
        logger.info("Executing use-case: ForgetPassword().");
        final var account = accountRepository.findByEmailWithThrow(email);
        final var forgetPassword = ForgetPassword.create(account.getAccountId());
        forgetPasswordRepository.save(forgetPassword);
        queueProducer.sendMessage(QUEUE_FORGET_PASSWORD, mappingToMail(account.getEmail(), forgetPassword.getHashCode(), forgetPassword.getExpirationDate()));
    }
}
