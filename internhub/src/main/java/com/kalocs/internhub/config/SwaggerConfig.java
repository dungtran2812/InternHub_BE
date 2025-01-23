package com.kalocs.internhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Log4j2
public class SwaggerConfig {
    @Value("${server.port}")
    private String baseURL;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("https://internhubbe-production.up.railway.app" + contextPath);
        devServer.setDescription("Server URL in Development environment");

        Contact myContact = new Contact();
        myContact.setName("Kalocs Company");
        myContact.setEmail("internhub.kalocs@gmail.com");
        log.info("Swagger: https://internhubbe-production.up.railway.app{}/swagger-ui/index.html", contextPath);

        Info information = new Info()
                .title("InternHub API")
                .version("1.0")
                .description("This Web API is for internhub website.")
                .contact(myContact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("Bearer Authentication")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Authentication");

        return new OpenAPI()
                .info(information)
                .servers(List.of(devServer))
                .components(new Components().addSecuritySchemes("Bearer Authentication", securityScheme))
                .addSecurityItem(securityRequirement);
    }
}