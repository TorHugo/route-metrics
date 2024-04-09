package vo;

import com.dev.torhugo.domain.vo.Email;
import com.dev.torhugo.domain.vo.HashCode;
import com.dev.torhugo.domain.vo.Password;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(expectedHashCode, result.getValue(), MESSAGE_TO_EQUAL);
    }
}
