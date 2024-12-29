package org.esteban.lescano.dmhouse;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class DmHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmHouseApplication.class, args);
    }

//config para titulo de documentacion
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API Digital Money House")
                        .version("1.0")
                        .description("API para la gestion de usuarios y transacciones de DMHouse")
                        .termsOfService("XXXXXXXXXXXXXXXXXXXXXXX")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("estebanles22@gmail.com")));
    }
}