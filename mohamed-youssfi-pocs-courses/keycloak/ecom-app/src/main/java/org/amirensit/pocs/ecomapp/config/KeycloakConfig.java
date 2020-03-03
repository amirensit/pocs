package org.amirensit.pocs.ecomapp.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    /**
     * Load Keycloak configuration from application.properties or application.yml, rather than keycloak.json.
     */
    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver () {
        return new KeycloakSpringBootConfigResolver();
    }
}
