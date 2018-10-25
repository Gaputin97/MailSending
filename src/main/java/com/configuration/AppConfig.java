package com.configuration;

import com.configuration.database.MongoTemplateConfiguration;
import com.configuration.interceptor.ApplicationInterceptorConfiguration;
import com.configuration.mail.JavaMailSenderConfiguration;
import com.configuration.rest.RestTemplateConfig;
import com.configuration.swagger.SwaggerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoTemplateConfiguration.class, ApplicationInterceptorConfiguration.class, JavaMailSenderConfiguration.class,
        RestTemplateConfig.class, SwaggerConfiguration.class})
public class AppConfig {

    private MongoTemplateConfiguration mongoTemplateConfiguration;
    private ApplicationInterceptorConfiguration applicationInterceptorConfiguration;
    private JavaMailSenderConfiguration javaMailSenderConfiguration;
    private RestTemplateConfig restTemplateConfig;
    private SwaggerConfiguration swaggerConfiguration;

    @Autowired
    public AppConfig(MongoTemplateConfiguration mongoTemplateConfiguration,
                     ApplicationInterceptorConfiguration applicationInterceptorConfiguration,
                     JavaMailSenderConfiguration javaMailSenderConfiguration,
                     RestTemplateConfig restTemplateConfig,
                     SwaggerConfiguration swaggerConfiguration) {
        this.mongoTemplateConfiguration = mongoTemplateConfiguration;
        this.applicationInterceptorConfiguration = applicationInterceptorConfiguration;
        this.javaMailSenderConfiguration = javaMailSenderConfiguration;
        this.restTemplateConfig = restTemplateConfig;
        this.swaggerConfiguration = swaggerConfiguration;
    }
}
