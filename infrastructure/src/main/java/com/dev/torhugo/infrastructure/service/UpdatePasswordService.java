package com.dev.torhugo.infrastructure.service;

import com.dev.torhugo.infrastructure.api.models.request.UpdatePasswordDTO;

public interface UpdatePasswordService {
    void updatePassword(final UpdatePasswordDTO request);
}
