package org.esteban.lescano.dmhouse.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DMHouse API")
                        .version("1.0")
                        .description("Documentación interactiva de la API de DMHouse")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("estebanles22@gmail.com")));
    }
}