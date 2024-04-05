package com.dev.torhugo.api.controller;

import com.dev.torhugo.api.ForgetPasswordAPI;
import com.dev.torhugo.api.models.ForgetPasswordDTO;
import com.dev.torhugo.service.ForgetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForgetPasswordController implements ForgetPasswordAPI {
    private final ForgetPasswordService forgetPasswordService;
    @Override
    public void forgetPassword(final ForgetPasswordDTO request) {
        forgetPasswordService.sendHash(request);
    }
}
