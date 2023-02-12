package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.model.jpa.User;
import com.client.ws.rasmooplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void given_uploadPhoto_when_receiveMultPartFile_then_return200Ok() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/static/logoJava.png");
        MockMultipartFile file = new MockMultipartFile("file", "logoJava.png", MediaType.MULTIPART_FORM_DATA_VALUE, fis);
        Mockito.when(userService.uploadPhoto(1L, file)).thenReturn(new User());
        MockMultipartHttpServletRequestBuilder builder =
                multipart("/user/1/upload-photo");
        builder.with(request -> {
            request.setMethod(HttpMethod.PATCH.name());
            return request;
        });

        mockMvc.perform(builder.file(file))
                .andExpect(status().isOk());
    }
}