package com.business.user.security;

import com.business.user.model.UserProfile;
import com.business.user.repository.MongoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private static final String URL_TO_AUTHENTICATION_DATA = "https://ec3-security-test-custom." +
            "eu-de.mybluemix.net/api/v1/person/profile";

    private static RestTemplate restTemplate;
    private static MongoUserRepository userRepository;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate, MongoUserRepository  userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    public static boolean isRightCredentials(HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, getAuthenticationHeaderString(request));

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        logger.info("Http Authorization Header: " + httpEntity);

        ResponseEntity<UserProfile> responseEntityOfUserProfile = restTemplate.exchange(URL_TO_AUTHENTICATION_DATA, HttpMethod.GET, httpEntity, UserProfile.class);

        UserProfile userProfile = responseEntityOfUserProfile.getBody();
        
        logger.info("UserProfile roles: " + userProfile.getRoles());

        return true;
    }

    private static String getAuthenticationHeaderString(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
