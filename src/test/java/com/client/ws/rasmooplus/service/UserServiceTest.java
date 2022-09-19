package com.client.ws.rasmooplus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserDetailsService userService;


    @Test
    void contextLoads() {

        userService.sendRecoveryCode("33ec5b17ef@boxomail.live");

    }
}
