package exception;

import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionTest extends MessageUtil {

    @Test
    void shoudThrowInvalidHashForgetPassowrdException(){
        // Given
        final var expectedMessage = "Invalid hash code!";

        // When
        final var exception = new InvalidHashForgetPasswordException(expectedMessage);

        // Then
        assertEquals(expectedMessage, exception.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shoudThrowRepositoryException(){
        // Given
        final var expectedMessage = "Repository not foud!";

        // When
        final var exception = new RepositoryException(expectedMessage);

        // Then
        assertEquals(expectedMessage, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
