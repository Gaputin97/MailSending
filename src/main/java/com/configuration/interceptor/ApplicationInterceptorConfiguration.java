package com.configuration.interceptor;

import com.business.user.interceptor.BasicSecurityAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfiguration implements WebMvcConfigurer {
    private BasicSecurityAuthenticationInterceptor basicSecurityAuthenticationInterceptor;

    @Autowired
    public ApplicationInterceptorConfiguration(BasicSecurityAuthenticationInterceptor
                                                           basicSecurityAuthenticationInterceptor) {
        this.basicSecurityAuthenticationInterceptor = basicSecurityAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicSecurityAuthenticationInterceptor).addPathPatterns("/**/simple", "/**/attachment",
                                                                                        "/**/html", "/**/image");
    }
}
