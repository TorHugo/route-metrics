package api;

import com.dev.torhugo.infrastructure.api.models.CoordinateDTO;
import com.dev.torhugo.infrastructure.api.models.request.BasicRouteDTO;
import com.dev.torhugo.application.usecase.CreateRouteUseCase;
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

class RouteControllerTest extends ControllerDefaultIT {
    @MockBean
    private CreateRouteUseCase createRouteUseCase;

    @Test
    void shouldCreateRouteWithSuccess() throws Exception {
        // Given
        final var expectedRouteId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new BasicRouteDTO(expectedAccountId, new CoordinateDTO(expectedLatitude, expectedLongitude));
        when(createRouteUseCase.execute(any())).thenReturn(expectedRouteId);

        // When
        final var request = MockMvcRequestBuilders
                .post("/route/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.mapper.writeValueAsString(input));

        final var response = super.mvc.perform(request).andDo(MockMvcResultHandlers.log());

        // Then
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.route_id", equalTo(expectedRouteId.toString())));
        verify(createRouteUseCase, times(1)).execute(any());
    }

}
