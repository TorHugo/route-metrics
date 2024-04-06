package com.dev.torhugo.infrastructure.service.impl;

import com.dev.torhugo.infrastructure.api.models.request.ForgetPasswordDTO;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.infrastructure.repository.ForgetPasswordRepository;
import com.dev.torhugo.infrastructure.repository.models.ForgetPasswordEntity;
import com.dev.torhugo.infrastructure.service.util.IdentifierUtils;
import com.dev.torhugo.infrastructure.service.ForgetPasswordService;
import com.dev.torhugo.infrastructure.service.dto.SendEmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.dev.torhugo.infrastructure.service.util.HashUtils.generateUniqueHash;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForgetPasswordServiceImpl implements ForgetPasswordService {
    private static final Integer HASH_SIZE = 6;
    private static final Long MINUTES_EXPIRATION_INTERVAL = 30L;
    private static final String QUEUE_FORGET_PASSWORD = "QUEUE_FORGET_PASSWORD";

    private final AccountRepository accountRepository;
    private final QueueProducer queueProducer;
    private final ForgetPasswordRepository forgetPasswordRepository;
    @Override
    public void sendHash(final ForgetPasswordDTO request) {
        log.info("Executing service: ForgetPassowrd.");
        final var account = accountRepository.findByEmailWithThrow(request.email());
        final var hashCode = generateHashCode();
        final var forgetEntity = mappingEntity(account, hashCode);
        forgetPasswordRepository.save(forgetEntity);
        queueProducer.sendMessage(QUEUE_FORGET_PASSWORD, mappingSendEmail(request.email(), hashCode, forgetEntity.getExpirationAt()));
    }

    private String generateHashCode() {
        var hashCode = "";
        boolean hashExists;

        do {
            hashCode = generateUniqueHash(HASH_SIZE);
            final var existsForget = forgetPasswordRepository.findByHashCodeIgnoreCaseAndActiveTrue(hashCode);
            hashExists = Objects.nonNull(existsForget);
        } while (hashExists);

        return hashCode;
    }

    private SendEmailDTO mappingSendEmail(final String email,
                                          final String hashCode,
                                          final LocalDateTime expirationDate) {
        return SendEmailDTO.builder()
                .email(email)
                .hash(hashCode)
                .expirationDate(expirationDate)
                .build();
    }

    private ForgetPasswordEntity mappingEntity(final Account account,
                                         final String hashCode) {
        final var forgetIdentifier = IdentifierUtils.generateIdentifier();
        final var isActive = true;
        final var dateNow = LocalDateTime.now();
        final var expirationDate = dateNow.plusMinutes(MINUTES_EXPIRATION_INTERVAL);

        return new ForgetPasswordEntity(
                forgetIdentifier,
                account.getAccountId(),
                hashCode,
                expirationDate,
                isActive,
                dateNow
                );
    }
}
