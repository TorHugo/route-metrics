package com.dev.torhugo.service;

import com.dev.torhugo.api.models.ForgetPasswordDTO;

public interface ForgetPasswordService {
    void sendHash(final ForgetPasswordDTO request);
}
