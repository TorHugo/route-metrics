package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.ForgetPasswordAPI;
import com.dev.torhugo.api.models.request.ConfirmHashDTO;
import com.dev.torhugo.api.models.request.ForgetPasswordDTO;
import com.dev.torhugo.api.models.request.UpdatePasswordDTO;
import com.dev.torhugo.service.ConfirmHashService;
import com.dev.torhugo.service.ForgetPasswordService;
import com.dev.torhugo.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class ForgetPasswordController implements ForgetPasswordAPI {
    private final ForgetPasswordService forgetPasswordService;
    private final ConfirmHashService confirmHashService;
    private final UpdatePasswordService updatePasswordService;

    @Override
    public void forgetPassword(final ForgetPasswordDTO request) {
        forgetPasswordService.sendHash(request);
    }

    @Override
    public ResponseEntity<?> confirmHashCode(final ConfirmHashDTO request) {
        final var token = confirmHashService.confirmation(request);
        return ok().header(SET_COOKIE, token.toString()).body(null);
    }

    @Override
    public void updatePassword(final UpdatePasswordDTO request) {
        updatePasswordService.updatePassword(request);
    }
}
