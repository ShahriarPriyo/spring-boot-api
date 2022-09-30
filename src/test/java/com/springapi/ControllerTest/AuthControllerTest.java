package com.springapi.ControllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapi.Entity.Users;
import com.springapi.JWTConfiguration.TokenProvider;
import com.springapi.JWTUtils.JWTUtility;
import com.springapi.Services.AuthServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @MockBean
    private AuthServices authService;

    @MockBean
    private TokenProvider tokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("POST user Sign up - Success")
    void testSignUp() throws Exception{
        Users mockUser = new Users(1,"Priyo","priyo98@gmail.com", "priyo123", "1798277732");
        Users signupUser = new Users(1,"Priyo","priyo98@gmail.com", "priyo123", "1798277732");
        String token = "sdjkjksdjk";
        doReturn(signupUser).when(authService).signUp(mockUser);
        doReturn(token).when(tokenProvider).provideToken(mockUser.getEmail());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUser)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST user Sign in - Success")
    void testSignIn() throws Exception{
        Users mockUser = new Users("priyo98@gmail.com", "priyo123");
        Users signinUser = new Users(1,"Priyo","priyo98@gmail.com", "priyo123", "1798277732");
        String token = "sdjkjksdjk";
        doReturn(Optional.of(signinUser)).when(authService).logIn(mockUser);
        doReturn(token).when(tokenProvider).provideToken(mockUser.getEmail());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUser)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST user Sign in - Password Not Match")
    void testSignInPassNotMatch() throws Exception{
        Users mockUser = new Users("priyo98@gmail.com", "123");
        when(authService.logIn(mockUser)).thenThrow(new RuntimeException());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUser)))
                .andExpect(status().isBadRequest());
    }

    private String asJsonString(final Users postUser) {
        try{
            return objectMapper.writeValueAsString(postUser);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
