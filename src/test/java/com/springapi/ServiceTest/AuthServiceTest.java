package com.springapi.ServiceTest;

import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import com.springapi.Services.AuthServices;
import com.springapi.Utils.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTest {
    @MockBean

    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AuthServices authServices;



    @Test
    @DisplayName("POST User sign in - Not Found")
    void testSignInUserNotFound(){
        Users mockUser = new Users(1, "Priyo","priyo98@gmail.com", "12345ab", "1798277732");
        Users signinUser = new Users( "priyo333444@gmail.com", "Saifur123");

        when(userRepository.findByEmail("priyo333444@gmail.com")).thenThrow(new EntityNotFoundException(AuthServiceTest.class," Email ","priyo333444@gmail.com"));

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> authServices.logIn(signinUser));
        String actualMessage = exception.getMessage();
        String expectedMessage = "AuthServiceTest not found with  Email  priyo333444@gmail.com";
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("POST User sign Up - User Email Exist")
    void testSignUpUserExist(){
        Users mockUser = new Users( "Priyo","priyo98@gmail.com", "12345ab", "1798277732");
        when(userRepository.findByEmail("priyo98@gmail.com")).thenThrow(new RuntimeException());

    }



    @Test
    @DisplayName("POST user login - Success")
    void testUserSignIn(){
        Users postUser = new Users( "priyo98@gmail.com", "12345ab");
        Users mockUser = new Users(1, "Priyo","priyo98@gmail.com", "12345ab", "1798277732");
        doReturn(Optional.of(mockUser)).when(userRepository).findByEmail("saif@gmail.com");

        doReturn(true).when(passwordEncoder).matches(postUser.getPassword(), "12345ab");

        Optional<Users> validUser = authServices.logIn(postUser);
        Assertions.assertEquals(mockUser, validUser.get());
    }

}
