package com.example.trackpot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for OpenAPI documentation.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures the OpenAPI documentation.
     *
     * @return the OpenAPI configuration
     */
    @Bean
    public OpenAPI trackpotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trackpot API")
                        .description("API for tracking snooker matches and scores")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Trackpot Team")
                                .email("support@trackpot.com")
                                .url("https://trackpot.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development server"),
                        new Server()
                                .url("https://api.trackpot.com")
                                .description("Production server")));
    }
}