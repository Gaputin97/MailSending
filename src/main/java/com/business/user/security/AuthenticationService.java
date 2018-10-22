package com.business.user.security;

import com.business.user.model.UserProfile;
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
    private static final int COUNT_OF_CHARACTERS_TO_ENCODED_USER_DETAILS = 6;
    private static final String URL_TO_AUTHENTICATION_DATA = "https://ec3-security-test-custom." +
            "eu-de.mybluemix.net/api/v1/person/profile";

    private static RestTemplate restTemplate;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static boolean isRightCredentials(HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, getAuthenticationHeaderString(request));

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        logger.info("httpEntity: " + httpEntity);
        restTemplate.exchange(URL_TO_AUTHENTICATION_DATA, HttpMethod.GET, httpEntity, UserProfile.class);

        logger.info("UserProfile roles: " + restTemplate.toString());

        return true;
    }

    private static String getAuthenticationHeaderString(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
