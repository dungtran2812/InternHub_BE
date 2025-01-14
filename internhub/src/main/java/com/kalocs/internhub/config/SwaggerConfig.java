package com.kalocs.internhub.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
@Log4j2
public class SwaggerConfig {
    @Value("${server.port}")
    private String BASE_URL;
    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(BASE_URL);
        devServer.setDescription("Server URL in Development environment");

        Contact myContact = new Contact();
        myContact.setName("Kalocs Company");
        myContact.setEmail("internhub.kalocs@gmail.com");
        log.info("Swagger: http://localhost:" + BASE_URL + CONTEXT_PATH + "/swagger-ui/index.html");
        Info information = new Info()
                .title("InternHub API")
                .version("1.0")
                .description("This Web API is for internhub website.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(devServer));
    }

}
