package com.dev.torhugo.infrastructure.service;

import com.dev.torhugo.infrastructure.api.models.request.ForgetPasswordDTO;

public interface ForgetPasswordService {
    void sendHash(final ForgetPasswordDTO request);
}
