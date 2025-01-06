package org.esteban.lescano.dmhouse.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("keycloak", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Autenticación con Keycloak")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("http://localhost:8080/realms/dmh/protocol/openid-connect/auth")
                                                .tokenUrl("http://localhost:8080/realms/dmh/protocol/openid-connect/token")
                                                .scopes(new Scopes())))))
                .addSecurityItem(new SecurityRequirement().addList("keycloak"))
                .info(new Info()
                        .title("DMHouse API")
                        .version("1.0")
                        .description("Documentación interactiva de la API de DMHouse")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("estebanles22@gmail.com")));
    }
}