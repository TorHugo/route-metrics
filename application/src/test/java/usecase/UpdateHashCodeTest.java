package usecase;

import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.UpdateHashUseCase;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateHashCodeTest extends MessageUtil {

    @InjectMocks
    UpdateHashUseCase updateHashUseCase;

    @Mock
    ForgetPasswordRepository forgetPasswordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateHashWithSuccess(){
        final var expectedAccountId = UUID.randomUUID();
        final var expectedForgetPassword = ForgetPassword.create(expectedAccountId);
        expectedForgetPassword.confirmed();
        when(forgetPasswordRepository.findHashConfirmedTrue(any(), any())).thenReturn(expectedForgetPassword);
        doNothing().when(forgetPasswordRepository).save(any());

        updateHashUseCase.execute(expectedForgetPassword.getHashCode(), expectedAccountId);

        verify(forgetPasswordRepository, times(1)).findHashConfirmedTrue(any(), any());
        verify(forgetPasswordRepository, times(1)).save(any());
    }
    @Test
    void shouldThrowExceptionUpdateHashWhenHashIsNotConfirmed(){
        final var expectedMessageError = "HashCode is not confirmed!";
        final var expectedAccountId = UUID.randomUUID();
        final var expectedForgetPassword = ForgetPassword.create(expectedAccountId);
        when(forgetPasswordRepository.findHashConfirmedTrue(any(), any())).thenReturn(expectedForgetPassword);
        doNothing().when(forgetPasswordRepository).save(any());

        final var result = assertThrows(InvalidHashForgetPasswordException.class, () -> updateHashUseCase.execute(expectedForgetPassword.getHashCode(), expectedAccountId));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedMessageError, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(forgetPasswordRepository, times(1)).findHashConfirmedTrue(any(), any());
        verify(forgetPasswordRepository, times(0)).save(any());
    }
}
