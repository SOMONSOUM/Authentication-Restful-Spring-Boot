package com.dptsi.SpringBootRestApi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    private static final String SCHEMA_NAME = "bearerAuth";
    private static final String SCHEMA = "bearer";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SCHEMA_NAME))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(SCHEMA, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(SCHEMA)
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .description("Bearer token")
                        )
                )
                .servers(List.of(new Server()
                                .url("http://localhost:8080")
                                .description("Local server")
                        )
                )
                .info(new Info()
                        .title("Spring Boot Rest API")
                        .version("1.0")
                        .description("Spring Boot Rest API")
                        .contact(new Contact()
                                .name("Spring Boot Rest API")
                                .email("sok.dara@moc.gov.kh")
                                .url("https://github.com/somonsoum")
                        )
                );
    }
}
