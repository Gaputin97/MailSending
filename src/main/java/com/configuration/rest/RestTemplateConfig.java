package com.configuration.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }
}
