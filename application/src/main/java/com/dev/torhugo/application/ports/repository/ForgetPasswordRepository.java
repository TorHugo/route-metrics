package com.dev.torhugo.application.ports.repository;

import com.dev.torhugo.domain.entity.ForgetPassword;

import java.util.List;
import java.util.UUID;

public interface ForgetPasswordRepository {
    void save(final ForgetPassword forgetPassword);
    ForgetPassword findHashConfirmedFalse(final String hashcode,
                                          final UUID accountId);
    ForgetPassword findHashConfirmedTrue(final String hashcode,
                                         final UUID accountId);
    ForgetPassword findHashByAccount(final UUID accountId);
    List<ForgetPassword> findAll();
}
