package com.amiqt.infrapulse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI/Swagger documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Infra-Pulse API")
                .version("1.0.0")
                .description("Production-grade infrastructure health monitoring microservice.")
                .contact(new Contact()
                    .name("Noor Azami (AmiQT)")
                    .url("https://amiqt.github.io/portfolio"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")));
    }
}
