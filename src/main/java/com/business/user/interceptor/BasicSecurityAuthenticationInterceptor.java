package com.business.user.interceptor;

import com.business.user.service.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class BasicSecurityAuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BasicSecurityAuthenticationInterceptor.class);
    private AuthenticationService authenticationService;

    @Autowired
    public BasicSecurityAuthenticationInterceptor(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Interceptor***********************************");
        if(authenticationService.isRightCredentials(request)) {
            return super.preHandle(request, response, handler);
        }
        return false;
    }
}
