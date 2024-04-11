package entity;

import com.dev.torhugo.domain.entity.Position;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest extends MessageUtil {

    @Test
    void test(){
        final var expectedRouteId = UUID.randomUUID();
        final var expectedValue = 0.0;
        final var startLatitude = -25.346860675274446;
        final var startLongitude = -49.17790747806736;

        final var newLatitude = -25.347472864987918;
        final var newLongitude = -49.17719453879971;

        final var otherLatitude = -25.347546864987918;
        final var otherLongitude = -49.17719453879971;

        final var position = Position.create(expectedRouteId, startLatitude, startLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertEquals(expectedValue, position.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(expectedValue, position.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(expectedValue, position.getDistance(), MESSAGE_TO_EQUAL);

        position.calculateDistanceAndVelocity(newLatitude, newLongitude);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);

        position.updatePosition(newLatitude, newLongitude);
        assertNotEquals(position.getCoordinate().latitude(), startLatitude, MESSAGE_NOT_EQUAL);
        assertNotEquals(position.getCoordinate().longitude(), startLongitude, MESSAGE_NOT_EQUAL);

        position.calculateDistanceAndVelocity(otherLatitude, otherLongitude);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);
    }
}
