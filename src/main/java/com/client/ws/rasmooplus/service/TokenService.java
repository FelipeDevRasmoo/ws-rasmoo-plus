package com.client.ws.rasmooplus.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String getToken(Authentication auth);

    Boolean isValid(String token);

    Long getUserId(String token);

}
