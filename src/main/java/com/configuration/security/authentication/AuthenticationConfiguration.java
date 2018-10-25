package com.configuration.security.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@PropertySource("classpath:endPoints.properties")
public class AuthenticationConfiguration {
    @Bean
    public URI getAuthenticationUriExternalResource(@Value("${authentication.point}") String authenticationEndpoint) {
        return URI.create(authenticationEndpoint);
    }
}
