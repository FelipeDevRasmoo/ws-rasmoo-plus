package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoudException;
import com.client.ws.rasmooplus.model.jpa.User;
import com.client.ws.rasmooplus.model.jpa.UserType;
import com.client.ws.rasmooplus.repositoy.jpa.UserRepository;
import com.client.ws.rasmooplus.repositoy.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto dto;

    @BeforeEach
    public void loadUser() {
        dto = new UserDto();
        dto.setId(1L);
        dto.setEmail("felipe@email.com");
        dto.setCpf("11111111111");
        dto.setUserTypeId(1L);
    }

    @Test
    void given_create_when_idIsNullAndUserTypeIsFound_then_returnUserCreated() {
        UserType userType = getUserType();

        when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));

        dto.setId(null);

        User user = getUser(userType);
        when(userRepository.save(user)).thenReturn(user);

        Assertions.assertEquals(user, userService.create(dto));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }




    @Test
    void given_create_when_idIsNotNull_then_throwBadRequestException() {

        Assertions.assertThrows(BadRequestException.class, () -> userService.create(dto));

        verify(userTypeRepository, times(0)).findById(any());
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void given_create_when_idIsNullAndUserTypeIsNotFound_then_throwNotFoudException() {
        dto.setId(null);
        when(userTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoudException.class, () -> userService.create(dto));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void given_uploadPhoto_when_thereIsUserAndFileAndItIsPNGorJPEG_then_updatePhotoAndReturnUser() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/static/logoJava.png");
        MockMultipartFile file = new MockMultipartFile("file", "logoJava.png", MediaType.MULTIPART_FORM_DATA_VALUE, fis);
        UserType userType = getUserType();
        User user = getUser(userType);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        User userReturned = userService.uploadPhoto(2L, file);
        assertNotNull(userReturned);
        assertNotNull(userReturned.getPhoto());
        assertEquals("logoJava.png", userReturned.getPhotoName());
        verify(userRepository, times(1)).findById(2L);
    }

    private UserType getUserType() {
        return new UserType(1L, "Aluno", "Aluno da plataforma");
    }

    private User getUser(UserType userType) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setDtSubscription(dto.getDtSubscription());
        user.setDtExpiration(dto.getDtExpiration());
        user.setUserType(userType);
        return user;
    }
}