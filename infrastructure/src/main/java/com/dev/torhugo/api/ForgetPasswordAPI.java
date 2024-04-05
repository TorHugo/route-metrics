package com.dev.torhugo.api;

import com.dev.torhugo.api.models.ForgetPasswordDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/forget-password")
public interface ForgetPasswordAPI {
    @PostMapping("/send-hash")
    void forgetPassword(final @Valid @RequestBody ForgetPasswordDTO request);
}
