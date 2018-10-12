package com.configuration.MongoDB;

import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@PropertySource("classpath:mongoDataBase.properties")
public class MongoTemplateConfiguration {

    @Value("${mongo.database.localhost}")
    private String localhost;

    @Value("${mongo.database.name}")
    private String dataBaseName;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(localhost);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(),dataBaseName);
    }
}
