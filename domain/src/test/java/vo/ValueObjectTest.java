package vo;

import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.vo.*;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValueObjectTest extends MessageUtil {

    @Test
    void shouldInitializeEmailWhenValidParams(){
        // Given
        final var expectedEmail = "account@test.com";
        // When
        final var result = new Email(expectedEmail);
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedEmail, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldInitializePasswordWhenValidParams(){
        // Given
        final var expectedPassword = "account_password";
        // When
        final var result = new Password(expectedPassword);
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedPassword, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldInitializeHashCodeWhenValidParams(){
        // Given
        final var expectedHashCode = "123456";
        // When
        final var result = new HashCode(expectedHashCode);
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedHashCode, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidHashCode(){
        final var expectedException = "Invalid signature for hash code!";
        final var expectedValue = "1234";

        final var result = assertThrows(InvalidArgumentException.class, () -> new HashCode(expectedValue));
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldInitializeVelocityWhenValidParams(){
        final var expectedVelocity = Math.random();

        final var result = new Velocity(expectedVelocity);

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedVelocity, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidVelocity(){
        final var expectedException = "Invalid velocity!";
        final var expectedValue = -0.1;

        final var result = assertThrows(InvalidArgumentException.class, () -> new Velocity(expectedValue));
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenVelocityIsNull(){
        final var expectedException = "Invalid velocity!";

        final var result = assertThrows(InvalidArgumentException.class, () -> new Velocity(null));
        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldInitializeStatusWhenValidParams(){
        final var expectedValue = "requested";

        final var result = new Status(expectedValue);

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedValue, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidStatus(){
        final var expectedValue = "status";
        final var expectedException = "Invalid status of ride!";

        final var result = assertThrows(InvalidArgumentException.class, () -> new Status(expectedValue));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldInitializeDistanceWhenValidParams(){
        final var expectedValue = Math.random();

        final var result = new Distance(expectedValue);

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedValue, result.value(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidDistance(){
        final var expectedException = "Invalid distance!";
        final var expectedValue = -0.1;

        final var result = assertThrows(InvalidArgumentException.class, () -> new Distance(expectedValue));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsNull(){
        final var expectedException = "Invalid distance!";

        final var result = assertThrows(InvalidArgumentException.class, () -> new Distance(null));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidLatitude(){
        final var expectedException = "Invalid latitude!";
        final var expectedValue = 91.0;
        final var expectedCorrectValue = -180.0;
        final var expectedTime = LocalDateTime.now();

        final var result = assertThrows(InvalidArgumentException.class, () -> new Coordinate(expectedValue, expectedCorrectValue, expectedTime));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidLatitudeNegative(){
        final var expectedException = "Invalid latitude!";
        final var expectedValue = -91.0;
        final var expectedCorrectValue = -180.0;
        final var expectedTime = LocalDateTime.now();

        final var result = assertThrows(InvalidArgumentException.class, () -> new Coordinate(expectedValue, expectedCorrectValue, expectedTime));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidLongitude(){
        final var expectedException = "Invalid longitude!";
        final var expectedValue = 181.0;
        final var expectedCorrectValue = -90.0;
        final var expectedTime = LocalDateTime.now();

        final var result = assertThrows(InvalidArgumentException.class, () -> new Coordinate(expectedCorrectValue, expectedValue, expectedTime));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidLongitudeNegative(){
        final var expectedException = "Invalid longitude!";
        final var expectedValue = -181.0;
        final var expectedCorrectValue = -90.0;
        final var expectedTime = LocalDateTime.now();

        final var result = assertThrows(InvalidArgumentException.class, () -> new Coordinate(expectedCorrectValue, expectedValue, expectedTime));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }
}
