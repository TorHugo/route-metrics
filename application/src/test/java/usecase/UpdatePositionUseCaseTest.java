package usecase;

import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.application.dto.UcUpdatePositionDTO;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.UpdatePositionUseCase;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.UseCaseMock.createPosition;
import static mock.UseCaseMock.createRoute;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdatePositionUseCaseTest extends MessageUtil {

    @InjectMocks
    UpdatePositionUseCase updatePositionUseCase;

    @Mock
    PositionRepository positionRepository;

    @Mock
    RouteRepository routeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdatePositionWithSuccess(){
        final var distance = 0.5003779552986967;
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var newLatitude = -22.9112;
        final var newLongitude = -43.1729;

        final var route = createRoute(startLatitude, startLongitude);
        route.confirm();
        final var position = createPosition(startLatitude, startLongitude);

        when(routeRepository.findById(any())).thenReturn(route);
        when(positionRepository.findPositionByRoute(any())).thenReturn(position);
        doNothing().when(routeRepository).save(any());
        doNothing().when(positionRepository).save(any());
        final var input = new UcUpdatePositionDTO(route.getRouteId(), new UcCoordinateDTO(newLatitude, newLongitude));

        final var result = updatePositionUseCase.execute(input);

        assertNotNull(result, MESSAGE_TO_EQUAL);
        assertEquals(newLatitude, result.lastCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(newLongitude, result.lastCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertNotNull(result.lastTime(), MESSAGE_NOT_NULL);
        assertNotNull(result.maxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(result.minVelocity(), MESSAGE_NOT_NULL);
        assertEquals(distance, result.distance(), MESSAGE_TO_EQUAL);
        assertNotNull(result.createdAt(), MESSAGE_NOT_NULL);

        verify(routeRepository, times(1)).findById(any());
        verify(routeRepository, times(1)).save(any());
        verify(positionRepository, times(1)).findPositionByRoute(any());
        verify(positionRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenInvalidRouteStatus(){
        final var expectedException = "Invalid route status!";
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var newLatitude = -22.9112;
        final var newLongitude = -43.1729;

        final var route = createRoute(startLatitude, startLongitude);
        final var position = createPosition(startLatitude, startLongitude);

        when(routeRepository.findById(any())).thenReturn(route);
        when(positionRepository.findPositionByRoute(any())).thenReturn(position);
        final var input = new UcUpdatePositionDTO(route.getRouteId(), new UcCoordinateDTO(newLatitude, newLongitude));

        final var result = assertThrows(InvalidArgumentException.class, () -> updatePositionUseCase.execute(input));

        assertNotNull(result, MESSAGE_TO_EQUAL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(routeRepository, times(1)).findById(any());
        verify(routeRepository, times(0)).save(any());
        verify(positionRepository, times(0)).findPositionByRoute(any());
        verify(positionRepository, times(0)).save(any());
    }

}
