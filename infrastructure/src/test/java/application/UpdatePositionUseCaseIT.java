package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.dto.UcUpdatePositionDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.usecase.ConfirmRouteUseCase;
import com.dev.torhugo.application.usecase.RequestRouteUseCase;
import com.dev.torhugo.application.usecase.UpdatePositionUseCase;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static mock.DefaultMockIT.createAccount;
import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class UpdatePositionUseCaseIT {

    @Autowired
    private UpdatePositionUseCase updatePositionUseCase;

    @Autowired
    private RequestRouteUseCase requestRouteUseCase;

    @Autowired
    private ConfirmRouteUseCase confirmRouteUseCase;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdatePositionWithSuccess(){
        final var account = createAccount();
        final var dateNow = LocalDateTime.now();
        accountRepository.save(account);
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var routeInput = new UcRouteDTO(account.getAccountId(), startLatitude, startLongitude);
        final var routeId = requestRouteUseCase.execute(routeInput);
        confirmRouteUseCase.execute(routeId, account.getEmail());

        final var newLatitude = -22.90016;
        final var newLongitude = -43.1729;
        final var updatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(newLatitude, newLongitude, dateNow));
        final var oldPosition = positionRepository.findPositionByRoute(routeId);
        updatePositionUseCase.execute(updatePosition);
        final var actualPosition = positionRepository.findPositionByRoute(routeId);

        assertNotNull(oldPosition, MESSAGE_NOT_NULL);
        final var valueZero = 0.0;
        assertEquals(startLatitude, oldPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(startLongitude, oldPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMinVelocity(), MESSAGE_TO_EQUAL);

        assertNotNull(actualPosition, MESSAGE_NOT_NULL);
        final var expectedDistanceStartToEnd = 0.7272159617008673;
        assertEquals(newLatitude, actualPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(newLongitude, actualPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistanceStartToEnd, actualPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(actualPosition.getMaxVelocity(), actualPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdatePositionWhenThreeValidPointsAndStraightLine(){
        final var account = createAccount();
        final var dateNow = LocalDateTime.now();
        accountRepository.save(account);
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var routeInput = new UcRouteDTO(account.getAccountId(), startLatitude, startLongitude);
        final var routeId = requestRouteUseCase.execute(routeInput);
        confirmRouteUseCase.execute(routeId, account.getEmail());

        final var newLatitude = -22.90016;
        final var newLongitude = -43.1729;
        final var updatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(newLatitude, newLongitude, dateNow));
        final var oldPosition = positionRepository.findPositionByRoute(routeId);
        updatePositionUseCase.execute(updatePosition);
        final var newPosition = positionRepository.findPositionByRoute(routeId);

        final var endLatitude = -22.8942;
        final var endLongitude = -43.1729;
        final var newUpdatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(endLatitude, endLongitude, dateNow));
        updatePositionUseCase.execute(newUpdatePosition);
        final var actualPosition = positionRepository.findPositionByRoute(routeId);

        assertNotNull(oldPosition, MESSAGE_NOT_NULL);
        final var valueZero = 0.0;
        assertEquals(startLatitude, oldPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(startLongitude, oldPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMinVelocity(), MESSAGE_TO_EQUAL);

        assertNotNull(newPosition, MESSAGE_NOT_NULL);
        final var expectedDistanceStartToEnd = 0.7272159617008673;
        assertEquals(newLatitude, newPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(newLongitude, newPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistanceStartToEnd, newPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertNotNull(newPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertNotNull(newPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(newPosition.getMaxVelocity(), newPosition.getMinVelocity(), MESSAGE_TO_EQUAL);

        assertNotNull(actualPosition, MESSAGE_NOT_NULL);
        final var expectedDistanceTotal = 1.3899387647184702;
        assertEquals(endLatitude, actualPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(endLongitude, actualPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistanceTotal, actualPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertNotEquals(actualPosition.getMaxVelocity(), actualPosition.getMinVelocity(), MESSAGE_NOT_EQUAL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdatePositionWhenThreeValidPointsAndNotStraightLine(){
        final var account = createAccount();
        final var dateNow = LocalDateTime.now();
        accountRepository.save(account);
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var routeInput = new UcRouteDTO(account.getAccountId(), startLatitude, startLongitude);
        final var routeId = requestRouteUseCase.execute(routeInput);
        confirmRouteUseCase.execute(routeId, account.getEmail());

        final var newLatitude = -22.9112;
        final var newLongitude = -43.1729;
        final var updatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(newLatitude, newLongitude, dateNow));
        final var oldPosition = positionRepository.findPositionByRoute(routeId);
        updatePositionUseCase.execute(updatePosition);
        final var newPosition = positionRepository.findPositionByRoute(routeId);

        final var endLatitude = -22.9157;
        final var endLongitude = -43.1750;
        final var newUpdatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(endLatitude, endLongitude, dateNow));
        updatePositionUseCase.execute(newUpdatePosition);
        final var actualPosition = positionRepository.findPositionByRoute(routeId);

        assertNotNull(oldPosition, MESSAGE_NOT_NULL);
        final var valueZero = 0.0;
        assertEquals(startLatitude, oldPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(startLongitude, oldPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, oldPosition.getMinVelocity(), MESSAGE_TO_EQUAL);

        assertNotNull(newPosition, MESSAGE_NOT_NULL);
        final var expectedDistanceStartToEnd = 0.5003779552986967;
        assertEquals(newLatitude, newPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(newLongitude, newPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistanceStartToEnd, newPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertNotNull(newPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertNotNull(newPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(newPosition.getMaxVelocity(), newPosition.getMinVelocity(), MESSAGE_TO_EQUAL);

        assertNotNull(actualPosition, MESSAGE_NOT_NULL);
        final var expectedDistanceTotal = 1.0450240776585642;
        assertEquals(endLatitude, actualPosition.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(endLongitude, actualPosition.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistanceTotal, actualPosition.getDistance(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertNotNull(actualPosition.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertNotEquals(actualPosition.getMaxVelocity(), actualPosition.getMinVelocity(), MESSAGE_NOT_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidStatus(){
        final var expectedException = "Invalid route status!";
        final var account = createAccount();
        final var dateNow = LocalDateTime.now();
        accountRepository.save(account);
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;
        final var routeInput = new UcRouteDTO(account.getAccountId(), startLatitude, startLongitude);
        final var routeId = requestRouteUseCase.execute(routeInput);

        final var newLatitude = -22.9112;
        final var newLongitude = -43.1729;
        final var updatePosition = new UcUpdatePositionDTO(routeId, new UcCoordinateDTO(newLatitude, newLongitude, dateNow));

        final var exception = assertThrows(InvalidArgumentException.class, () -> updatePositionUseCase.execute(updatePosition));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
