package org.esteban.lescano.dmhouse;

import org.esteban.lescano.dmhouse.security.JwtAuthConverterProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtAuthConverterProperties.class)
public class DmHouseApplication {

public static void main(String[] args) {
	SpringApplication.run(DmHouseApplication.class, args);
}

}