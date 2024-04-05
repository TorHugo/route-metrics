package com.dev.torhugo.service;

import com.dev.torhugo.api.models.request.ConfirmHashDTO;
import org.springframework.http.ResponseCookie;

public interface ConfirmHashService {
    ResponseCookie confirmation(final ConfirmHashDTO request);
}
