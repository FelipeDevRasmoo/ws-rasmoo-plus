package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.model.jpa.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserTypeServiceTest {

    @MockBean
    private UserTypeService userTypeService;

    @Test
    void findAll() {
        List<UserType> userTypeList = new ArrayList<>();

        UserType userType1 = new UserType(1l, "Professor","Professor da plataforma");
        UserType userType2 = new UserType(2l, "Administrador", "Funcionario");
        userTypeList.add(userType1);
        userTypeList.add(userType2);

        Mockito.when(userTypeService.findAll()).thenReturn(userTypeList);
        var result = userTypeService.findAll();
        Assertions.assertThat(result).isNotEmpty();
    }
}
