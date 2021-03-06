package com.configuration.interceptor;

import com.business.user.interceptor.BasicSecurityAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ApplicationInterceptorConfiguration implements WebMvcConfigurer {
    private BasicSecurityAuthenticationInterceptor basicSecurityAuthenticationInterceptor;

    @Autowired
    public ApplicationInterceptorConfiguration(BasicSecurityAuthenticationInterceptor basicSecurityAuthenticationInterceptor) {
        this.basicSecurityAuthenticationInterceptor = basicSecurityAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicSecurityAuthenticationInterceptor).excludePathPatterns("/swagger-resources/configuration/security",
                "/swagger-resources", "/v2/api-docs", "/swagger-resources/configuration/ui");
    }
}
