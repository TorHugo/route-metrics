package com.dev.torhugo.application.mappers;

import com.dev.torhugo.application.dto.messaging.ForgetPasswordMailDTO;

import java.time.LocalDateTime;

public class ForgetPasswordMapper {
    private ForgetPasswordMapper(){}

    public static ForgetPasswordMailDTO mappingToMail(final String email,
                                                      final String hash,
                                                      final LocalDateTime expiration){
        return new ForgetPasswordMailDTO(email, hash, expiration);
    }
}
