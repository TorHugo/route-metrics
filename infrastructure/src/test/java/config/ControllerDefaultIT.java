package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class ControllerDefaultIT extends AnnotationDefaultIT {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
    public final static String expectedMessageEqual = "Given values, both must be equal.";
    public final static String expectedMessageOptionalTrue = "This optional is not empty!";
    public final static String expectedMessageAccountNotFound = "Account not found!";
    public final static boolean expectedTrue = true;
    public final static boolean expectedFalse = false;
}
