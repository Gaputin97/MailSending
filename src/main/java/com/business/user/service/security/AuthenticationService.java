package com.business.user.service.security;

import com.business.user.model.UserProfile;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
@PropertySource("application.properties")
public class  AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private RestTemplate restTemplate;
    private String authenticationURI;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate,@Value("${authentication.point}") String authenticationURI) {
        this.restTemplate = restTemplate;
        this.authenticationURI = authenticationURI;
    }

    public boolean isRightCredentials(HttpServletRequest request)  {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        logger.info("Http Authorization Header: " + httpEntity);

        try {
            ResponseEntity<UserProfile> responseEntityOfUserProfile = restTemplate.exchange(authenticationURI, HttpMethod.GET,
                    httpEntity, UserProfile.class);

            logger.info("User roles: " + responseEntityOfUserProfile.getBody().getRoles());
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            int statusCode = exception.getStatusCode().value();
            logger.info("Status Code: " + statusCode);
            switch (statusCode) {
                case 401 : throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                case 403 : throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                case 404 : throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                case 500 : throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
        return true;
    }
}
