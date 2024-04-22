package com.dev.torhugo.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RouteMetricsApplication {
    public static void main(String[] args) {
        log.info("Starting RouteMetrics.");
        SpringApplication.run(RouteMetricsApplication.class, args);
    }

}
