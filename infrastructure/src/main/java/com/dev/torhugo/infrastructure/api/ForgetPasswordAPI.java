package com.dev.torhugo.infrastructure.api;

import com.dev.torhugo.infrastructure.api.models.request.ConfirmHashDTO;
import com.dev.torhugo.infrastructure.api.models.request.ForgetPasswordDTO;
import com.dev.torhugo.infrastructure.api.models.request.UpdatePasswordDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/forget-password")
public interface ForgetPasswordAPI {
    @PostMapping("/send-hash")
    void forgetPassword(final @Valid @RequestBody ForgetPasswordDTO request);
    @PostMapping("/confirm-hash")
    ResponseEntity<?> confirmHashCode(final @Valid @RequestBody ConfirmHashDTO request);
    @PutMapping("/update-password")
    void updatePassword(final @Valid @RequestBody UpdatePasswordDTO request);
}
