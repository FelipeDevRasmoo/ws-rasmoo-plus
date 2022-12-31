package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.dto.LoginDto;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.dto.TokenDto;
import com.client.ws.rasmooplus.dto.UserDetailsDto;
import com.client.ws.rasmooplus.model.jpa.SubscriptionType;
import com.client.ws.rasmooplus.model.redis.UserRecoveryCode;
import com.client.ws.rasmooplus.service.AuthenticationService;
import com.client.ws.rasmooplus.service.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
class AuthenticationControllerTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void given_auth_when_dtoIsOk_then_returnTokenDto() throws Exception {

        LoginDto dto = new LoginDto("usuario@usuario.com","pass");
        TokenDto tokenDto = new TokenDto(TOKEN, "Bearer");

        when(authenticationService.auth(dto)).thenReturn(tokenDto);

        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token",is(TOKEN)))
                .andExpect(jsonPath("$.type",is("Bearer")));
    }

    @Test
    void given_auth_when_dtoIsMissingValues_then_returnBadRequest() throws Exception {
        LoginDto dto = new LoginDto(""," ");
        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("[password=atributo obrigatório, username=atributo obrigatório]")))
                .andExpect(jsonPath("$.httpStatus",is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode",is(400)));
    }

    @Test
    void given_sendRecoveryCode_when_dtoIsOk_then_returnNoContent() throws Exception {

        UserRecoveryCode dto = new UserRecoveryCode();
        dto.setEmail("usuario@usuario.com");

        mockMvc.perform(post("/auth/recovery-code/send")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void given_sendRecoveryCode_when_emailIsNotSentOrWrong_then_returnBadRequest() throws Exception {

        UserRecoveryCode dto = new UserRecoveryCode();
        dto.setEmail("usuario");

        mockMvc.perform(post("/auth/recovery-code/send")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("[email=inválido]")))
                .andExpect(jsonPath("$.httpStatus",is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode",is(400)));
    }

    @Test
    void given_updatePasswordByRecoveryCode_when_dtoIsOk_then_returnNoContent() throws Exception {

        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmail("usuario@usuario.com");
        dto.setPassword("pass");
        dto.setRecoveryCode("808921");

        mockMvc.perform(patch("/auth/recovery-code/password")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void given_updatePasswordByRecoveryCode_when_emailIsWrongAndPassIsNull_then_returnBadRequest() throws Exception {

        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmail("usuario");
        dto.setPassword(" ");
        dto.setRecoveryCode("808921");

        mockMvc.perform(patch("/auth/recovery-code/password")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("[password=atributo inválido, email=inválido]")))
                .andExpect(jsonPath("$.httpStatus",is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode",is(400)));
    }
}