package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.RequestRouteUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class RequestRouteUseCaseIT {

    @Autowired
    private RequestRouteUseCase requestRouteUseCase;

    @Autowired
    private RouteRepository  routeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldRequestRouteWithSuccess(){
        final var name = "account test";
        final var email = "example@email.com";
        final var password = "password@";
        final var account = Account.create(name, email, password);
        accountRepository.save(account);
        accountRepository.findByAccountId(account.getAccountId());
        final var latitude = -25.346860675274446;
        final var longitude = -49.17790747806736;

        final var input = new UcRouteDTO(account.getAccountId(), latitude, longitude);
        final var route = requestRouteUseCase.execute(input);
        assertNotNull(route, MESSAGE_NOT_NULL);

        final var routeEntity = routeRepository.findById(route);
        assertNotNull(routeEntity, MESSAGE_NOT_NULL);
        assertEquals(latitude, routeEntity.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(longitude, routeEntity.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertNotNull(routeEntity.getStartCoordinate().time(), MESSAGE_NOT_NULL);
        assertEquals("requested", routeEntity.getStatus(), MESSAGE_TO_EQUAL);
        assertTrue(routeEntity.isActive(), MESSAGE_TRUE);
        assertNull(routeEntity.getName(), MESSAGE_NULL);

        final var positionEntity = positionRepository.findPositionByRoute(route);
        final var valueZero = 0.0;
        assertNotNull(positionEntity, MESSAGE_NOT_NULL);
        assertEquals(routeEntity.getStartCoordinate().latitude(), positionEntity.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(routeEntity.getStartCoordinate().longitude(), positionEntity.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertNotNull(positionEntity.getCoordinate().time(), MESSAGE_NOT_NULL);
        assertEquals(positionEntity.getDistance(), valueZero, MESSAGE_TO_EQUAL);
        assertEquals(positionEntity.getMaxVelocity(), valueZero, MESSAGE_TO_EQUAL);
        assertEquals(positionEntity.getMinVelocity(), valueZero, MESSAGE_TO_EQUAL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenAccountNotFound(){
        final var expectedException = "Account not found!";
        final var latitude = -25.346860675274446;
        final var longitude = -49.17790747806736;

        final var input = new UcRouteDTO(UUID.randomUUID(), latitude, longitude);
        final var exception = assertThrows(RepositoryException.class, () -> requestRouteUseCase.execute(input));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenAccountPendingRoute(){
        final var expectedException = "This account has pending routes!";
        final var name = "account test";
        final var email = "example@email.com";
        final var password = "password@";
        final var account = Account.create(name, email, password);
        accountRepository.save(account);
        accountRepository.findByAccountId(account.getAccountId());
        final var latitude = -25.346860675274446;
        final var longitude = -49.17790747806736;

        final var input = new UcRouteDTO(account.getAccountId(), latitude, longitude);
        final var route = requestRouteUseCase.execute(input);
        assertNotNull(route, MESSAGE_NOT_NULL);

        final var exception = assertThrows(InvalidArgumentException.class, () -> requestRouteUseCase.execute(input));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
