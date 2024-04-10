package usecase;

import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindRouteUseCase;
import com.dev.torhugo.domain.entity.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FindRouteUseCaseTest extends MessageUtil {
    @InjectMocks
    FindRouteUseCase useCase;
    @Mock
    RouteRepository routeRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindRouteWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var route = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );
        when(routeRepository.findByIdAndAccount(any(), any())).thenReturn(route);

        // When
        final var result = useCase.execute(route.getRouteId(), "usr_email@example.com");

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(route.getRouteId(), result.routeId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getAccountId(), result.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getDistance(), result.distance(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStatus(), result.status(), MESSAGE_TO_EQUAL);
        assertEquals(route.isActive(), result.active(), MESSAGE_TO_EQUAL);
        assertEquals(route.getInitialCoord().latitude(), result.initialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getInitialCoord().longitude(), result.initialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getLastCoord().latitude(), result.lastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getLastCoord().longitude(), result.lastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getCreatedAt(), result.createdAt(), MESSAGE_TO_EQUAL);
        assertEquals(route.getUpdatedAt(), result.updatedAt(), MESSAGE_TO_EQUAL);
    }
}
