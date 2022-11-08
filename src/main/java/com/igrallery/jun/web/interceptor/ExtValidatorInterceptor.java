package com.igrallery.jun.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExtValidatorInterceptor implements HandlerInterceptor {

    private final String[] allowExt = {"png", "jpg"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    private boolean contains (String ext) {
        for (String e : allowExt) {
            if (e.equals(ext)) { return true; }
        }
        return false;
    }
}
