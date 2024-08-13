package com.sai.Uniblox.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


import static com.sai.Uniblox.constants.ApiConstants.TOKEN;

public class AuthorizationHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.contains("Bearer ")) {
            throw new RuntimeException("Missing Authorization Token");
        }
        String token = authorizationHeader.split("Bearer ")[1];
        boolean match = token.equals(TOKEN);
        if (!match) {
            throw new RuntimeException("Invalid Token");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
