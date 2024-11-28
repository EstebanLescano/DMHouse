package org.esteban.lescano.dmhouse.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Created by: Esteban Lescano
 * Date: 02/06/2022
 * esta clase solamente sera el dto de la clase JwtAuthConverter
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt-auth-converter")
@Setter
@Getter
public class JwtAuthConverterProperties {

    private  String resourceId;
    private String principalAttribute;

}
