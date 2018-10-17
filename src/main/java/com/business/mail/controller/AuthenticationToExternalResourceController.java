package com.business.mail.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Controller
@RequestMapping(value = "/test", consumes = "application/json", produces = "application/json")
public class AuthenticationToExternalResourceController {
    private static final String C3_URL = "https://ec3-security-dev-custom.eu-de.mybluemix.net/api/v1/person/profile";
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationToExternalResourceController.class);

    @GetMapping(value = "/send")
    public ResponseEntity sendGet() throws IllegalStateException, IOException {
        URL url = new URL(C3_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String userCredentials = "seva.user@ibm.com:1234";
        String basicAuthHeader = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        connection.setRequestProperty("Authorization", basicAuthHeader);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(connection.getInputStream());
        JsonNode groupNode = rootNode.findPath("roles");
        logger.warn("Type of response: " + connection.getContentType());
        logger.info("Roles node: " + groupNode);

        connection.disconnect();
        return new ResponseEntity(rootNode, HttpStatus.OK);
    }
}
