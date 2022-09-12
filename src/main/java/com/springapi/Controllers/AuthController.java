package com.springapi.Controllers;

import com.springapi.Entity.Users;
import com.springapi.JWTConfiguration.AuthProvider;
import com.springapi.Services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private AuthServices authServices;

    @Autowired
    private AuthProvider authProvider;

    @PostMapping(value = "/signup")
    public ResponseEntity<Users> signUp(@RequestBody Users user){
        try{
            Users newUser = authServices.signUp(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newUser);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<? extends Object> logIn(@RequestBody Users user){

        System.out.println(user.getEmail());
        try{
            Optional <Users> existedUser = authServices.logIn(user);
            System.out.println("Bro "+existedUser.get().getEmail()+" "+existedUser.get().getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(authProvider.provideToken(existedUser.get().getEmail()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
