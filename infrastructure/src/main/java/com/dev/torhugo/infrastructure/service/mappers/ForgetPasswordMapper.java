package com.dev.torhugo.infrastructure.service.mappers;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.infrastructure.repository.models.ForgetPasswordEntity;
import com.dev.torhugo.infrastructure.service.dto.SendEmailDTO;
import com.dev.torhugo.infrastructure.service.util.IdentifierUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ForgetPasswordMapper {
    private static final Long MINUTES_EXPIRATION_INTERVAL = 30L;
    public SendEmailDTO mappingSendEmail(final String email,
                                          final String hashCode,
                                          final LocalDateTime expirationDate) {
        return SendEmailDTO.builder()
                .emailTo(email)
                .hash(hashCode)
                .expirationDate(expirationDate)
                .build();
    }

    public ForgetPasswordEntity mappingToEntity(final Account account,
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
