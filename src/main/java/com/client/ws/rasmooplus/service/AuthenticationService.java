package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.LoginDto;
import com.client.ws.rasmooplus.dto.TokenDto;

public interface AuthenticationService {

    TokenDto auth(LoginDto dto);

}
