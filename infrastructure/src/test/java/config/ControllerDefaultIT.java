package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class ControllerDefaultIT extends AnnotationDefaultIT {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
}
