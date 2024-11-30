package org.esteban.lescano.dmhouse.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

/**
 * Created by: Esteban Lescano
 * Date: 02/06/2022
 * esta clase solamente sera el dto de la clase JwtAuthConverter
 */
@Validated
@Configuration
@Primary
@ConfigurationProperties(prefix = "jwt-auth-converter")
public class JwtAuthConverterProperties {

    private  String resourceId;
    private String principalAttribute;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getPrincipalAttribute() {
        return principalAttribute;
    }

    public void setPrincipalAttribute(String principalAttribute) {
        this.principalAttribute = principalAttribute;
    }
}
