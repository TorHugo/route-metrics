package config;

import com.dev.torhugo.configuration.WebServerConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = WebServerConfig.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AnnotationDefaultIT {
    public static final String messageToEqual = "Given values, both must be equal.";
    public static final String messageNotNull = "This value must not be null.";
    public static final String messageNull = "This value must be null.";
    public static final String messageTrue = "This value must be true.";
    public static final String messageFalse = "This value must be false.";
}

