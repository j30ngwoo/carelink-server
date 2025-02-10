package com.blaybus.server.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    String contextPath;

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityItem = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .openapi("3.0.1")  // OpenAPI 버전 명시
                .addServersItem(new Server().url(contextPath + "/"))
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(List.of(securityItem))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("CareLink")
                .description("CareLink API Documentation")
                .version("1.0.0");
    }
}

