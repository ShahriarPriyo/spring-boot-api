package com.springapi.ControllerTest;

import com.springapi.Entity.Users;
import com.springapi.Services.UserServices;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    private UserServices services;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET/user/1 - Found")
    void testgetUser() throws Exception{
        Users mockUser = new Users(1,"Priyo","priyo98@gmail.com","12345ab","01732210020");
        doReturn(Optional.of(mockUser)).when(services).getUser(1);

         mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{id}", 1))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Priyo"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("priyo98@gmail.com"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("12345ab"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("01732210020"));

    }
    @Test
    void deleteUser() throws Exception{
        Users mockUser= new Users(1,"Priyo","priyo98@gmail.com","12345ab","01732210020");
        given(services.getUser(1)).willReturn(Optional.of(mockUser));
        doNothing().when(services).deleteUser(mockUser.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Priyo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("priyo98@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("12345ab"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("01732210020"));
    }

}
