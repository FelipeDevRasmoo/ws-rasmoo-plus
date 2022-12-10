package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.jpa.User;
import com.client.ws.rasmooplus.model.jpa.UserType;
import com.client.ws.rasmooplus.repositoy.jpa.UserRepository;
import com.client.ws.rasmooplus.repositoy.jpa.UserTypeRepository;
import com.client.ws.rasmooplus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  UserTypeRepository userTypeRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void given_create_when_idIsNotNullAndUserIsFound_then_returnUserCreated(){

        UserDto dto = new UserDto();
        dto.setEmail("felipe@email.com");
        dto.setCpf("11111111111");
        dto.setUserTypeId(1L);

        UserType userType = new UserType(1L, "Aluno", "Aluno da plataforma");

        when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setDtSubscription(dto.getDtSubscription());
        user.setDtExpiration(dto.getDtExpiration());
        user.setUserType(userType);
        when(userRepository.save(user)).thenReturn(user);

        Assertions.assertEquals(user,userService.create(dto));

        verify(userTypeRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }


}