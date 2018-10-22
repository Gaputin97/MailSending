package com.business.user.interceptor;

import com.business.user.security.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class BasicSecurityAuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthenticationService.isRightCredentials(request);
        return super.preHandle(request, response, handler);
    }
}
