package com.amiqt.infrapulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Infra-Pulse.
 * This microservice monitors infrastructure health and exposes metrics.
 */
@SpringBootApplication
public class InfraPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraPulseApplication.class, args);
	}

}
