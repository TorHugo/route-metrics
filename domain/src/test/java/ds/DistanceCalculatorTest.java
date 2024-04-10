package ds;

import com.dev.torhugo.domain.ds.DistanceCalculator;
import com.dev.torhugo.domain.ds.VelocityCalculator;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DistanceCalculatorTest extends MessageUtil {

    @Test
    void test(){
        final var expectedDistance = 0.0988264294249355;
        final var expectedVelocity = 177.8875729648839;

        final var startLatitude = -25.346860675274446;
        final var startLongitude = -49.17790747806736;
        final var startTime = LocalDateTime.of(2024, 4, 10, 19, 26, 0);

        final var endLatitude = -25.347472864987918;
        final var endLongitude = -49.17719453879971;
        final var endTime = LocalDateTime.of(2024, 4, 10, 19, 26, 2);

        final var distance = DistanceCalculator.calculateDistance(startLatitude, startLongitude, endLatitude, endLongitude);
        final var velocity = VelocityCalculator.calculateVelocity(distance, startTime, endTime);
        assertNotNull(distance, MESSAGE_NOT_NULL);
        assertNotNull(velocity, MESSAGE_NOT_NULL);
        assertEquals(expectedDistance, distance, MESSAGE_TO_EQUAL);
        assertEquals(expectedVelocity, velocity, MESSAGE_TO_EQUAL);
    }
}
