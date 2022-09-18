package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.jpa.User;

public interface UserService {

    User create(UserDto dto);

    Object sendRecoveryCode(String email);
}
