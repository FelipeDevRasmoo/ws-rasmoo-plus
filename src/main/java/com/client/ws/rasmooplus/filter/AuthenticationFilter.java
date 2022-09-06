package com.client.ws.rasmooplus.filter;

import com.client.ws.rasmooplus.exception.BadRequestException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);

        filterChain.doFilter(request,response);
    }

    private String getBearerToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }


}
