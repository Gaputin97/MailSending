package com.configuration.database;

import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

@PropertySource("classpath:mongoDataBase.properties")
public class MongoTemplateConfiguration {
    private MongoClient mongoClient(String host) {
        return new MongoClient(host);
    }

    @Bean
    public MongoTemplate mongoTemplate(@Value("${mongo.database.name}") String dataBaseName,
                                       @Value("${mongo.database.host}") String host) {
        return new MongoTemplate(mongoClient(host), dataBaseName);
    }
}
