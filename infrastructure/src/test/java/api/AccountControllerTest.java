package api;

import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;
import config.ControllerDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends ControllerDefaultIT {

    @MockBean
    private CreateAccountUseCase createAccountUseCase;
    @MockBean
    private FindAccountUseCase findAccountUseCase;

    @Test
    void shouldCreatedAccountWithSuccess() throws Exception {
        // Given
        final var expectedAccountId = UUID.fromString("89e77128-c7f5-42dc-8180-6a77a063b42c");
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);
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
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);
        when(createAccountUseCase.execute(any()))
                .thenThrow(new InvalidArgumentException("Invalid email!"));

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


    @Test
    void shouldFindAccountWithSuccess() throws Exception {
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedDateNow = LocalDateTime.now();
        final var basicAccount = new UcBasicAccountDTO(expectedAccountId, expectedName, expectedEmail, true, false, expectedDateNow, expectedDateNow, null);
        when(findAccountUseCase.execute(any())).thenReturn(basicAccount);

        // When
        final var request = MockMvcRequestBuilders
                .get("/account/find/{accountId}", expectedAccountId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        final var response = super.mvc.perform(request).andDo(MockMvcResultHandlers.log());

        // Then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id", equalTo(expectedAccountId.toString())))
                .andExpect(jsonPath("$.name", equalTo(expectedName)))
                .andExpect(jsonPath("$.email", equalTo(expectedEmail)))
                .andExpect(jsonPath("$.in_active", equalTo(true)))
                .andExpect(jsonPath("$.in_admin", equalTo(false)));
        verify(findAccountUseCase, times(1)).execute(any());
    }
}
