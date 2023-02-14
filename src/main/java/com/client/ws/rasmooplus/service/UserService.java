package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.jpa.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User create(UserDto dto);

    User uploadPhoto(Long id, MultipartFile file) throws IOException;

    byte[] downloadPhoto(long l);
}
