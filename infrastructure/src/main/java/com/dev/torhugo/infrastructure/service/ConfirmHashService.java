package com.dev.torhugo.infrastructure.service;

import com.dev.torhugo.infrastructure.api.models.request.ConfirmHashDTO;
import org.springframework.http.ResponseCookie;

public interface ConfirmHashService {
    ResponseCookie confirmation(final ConfirmHashDTO request);
}
