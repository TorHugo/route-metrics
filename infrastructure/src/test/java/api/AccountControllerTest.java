package api;

import com.dev.torhugo.CreateAccountUseCase;
import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import com.dev.torhugo.models.AccountInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import config.ControllerDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends ControllerDefaultIT {

    @MockBean
    private CreateAccountUseCase createAccountUseCase;

    @Test
    void shouldCreatedAccountWithSuccess() throws Exception {
        // Given
        final var expectedAccountId = UUID.fromString("89e77128-c7f5-42dc-8180-6a77a063b42c");
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new AccountInput(expectedName, expectedEmail, expectedPassword);
        when(createAccountUseCase.execute(any())).thenReturn(expectedAccountId);

        // When
        final var request = MockMvcRequestBuilders
                .post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.mapper.writeValueAsString(input));

        final var response = super.mvc.perform(request).andDo(MockMvcResultHandlers.log());

        // Then
        response
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account_id", equalTo(expectedAccountId.toString())));
        verify(createAccountUseCase, times(1)).execute(any());
    }

    @Test
    void shouldInvalidParametersNotCreateNewAccount() throws Exception {
        // Given
        final var expectedError = "Invalid email!";
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test_dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new AccountInput(expectedName, expectedEmail, expectedPassword);
        when(createAccountUseCase.execute(any()))
                .thenThrow(new InvalidArgumentError("Invalid email!"));

        // When
        final var request = MockMvcRequestBuilders
                .post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(input));

        final var response = this.mvc.perform(request).andDo(MockMvcResultHandlers.log());

        // Then
        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo(expectedError)));
        verify(createAccountUseCase, times(1)).execute(any());
    }
}
