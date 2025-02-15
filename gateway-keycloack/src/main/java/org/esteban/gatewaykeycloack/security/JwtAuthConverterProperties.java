package org.esteban.gatewaykeycloack.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt-auth-converter")
public class JwtAuthConverterProperties {
    private String resourceId;
    private String principalAtribute;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getPrincipalAtribute() {
        return principalAtribute;
    }

    public void setPrincipalAtribute(String principalAtribute) {
        this.principalAtribute = principalAtribute;
    }
}
