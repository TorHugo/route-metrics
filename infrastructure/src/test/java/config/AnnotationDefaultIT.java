package config;

import com.dev.torhugo.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = WebServerConfig.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AnnotationDefaultIT {
    public static final String MESSAGE_TO_EQUAL = "Given values, both must be equal.";
    public static final String MESSAGE_NOT_NULL = "This value must not be null.";
    public static final String MESSAGE_NULL = "This value must be null.";
    public static final String MESSAGE_TRUE = "This value must be true.";
    public static final String MESSAGE_FALSE = "This value must be false.";
}

