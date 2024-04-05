package com.dev.torhugo.service;

import com.dev.torhugo.api.models.request.UpdatePasswordDTO;

public interface UpdatePasswordService {
    void updatePassword(final UpdatePasswordDTO request);
}
