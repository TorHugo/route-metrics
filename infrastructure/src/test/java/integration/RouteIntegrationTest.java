package integration;

import com.dev.torhugo.dtos.UcAccountDTO;
import com.dev.torhugo.dtos.UcRouteDTO;
import com.dev.torhugo.usecase.CreateAccountUseCase;
import com.dev.torhugo.usecase.CreateRouteUseCase;
import com.dev.torhugo.usecase.FindRouteUseCase;
import config.AnnotationDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

class RouteIntegrationTest extends AnnotationDefaultIT {
    @Autowired
    private CreateRouteUseCase createRouteUseCase;
    @Autowired
    private CreateAccountUseCase createAccountUseCase;
    @Autowired
    private FindRouteUseCase findRouteUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldCreateRouteWhenValidParams(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);
        final var expectedAccountId = createAccountUseCase.execute(expectedAccount);
        assertNotNull(expectedAccountId, MESSAGE_NOT_NULL);

        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedStatus = "REQUESTED";
        final var expectedActive = true;
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);

        // When
        final var routeId = createRouteUseCase.execute(input);
        assertNotNull(routeId, MESSAGE_NOT_NULL);
        final var savedRoute = findRouteUseCase.execute(routeId);
        assertNotNull(savedRoute, MESSAGE_NOT_NULL);

        // Then
        assertEquals(routeId, savedRoute.routeId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, savedRoute.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, savedRoute.initialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, savedRoute.initialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, savedRoute.lastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, savedRoute.lastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStatus, savedRoute.status(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, savedRoute.active(), MESSAGE_TO_EQUAL);
        assertNotNull(savedRoute.createdAt(), MESSAGE_NOT_NULL);
        assertNull(savedRoute.updatedAt(), MESSAGE_NULL);
    }
}
