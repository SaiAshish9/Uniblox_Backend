package com.sai.Uniblox.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sai.Uniblox.constants.ApiConstants.API_TOKEN;

@Service
public class AuthorizationHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing Authorization Token");
        } else {
            String token = authorizationHeader.split("Bearer ")[1];
            boolean match = token.equals(API_TOKEN);
            if (!match) {
                throw new RuntimeException("Invalid Token");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
