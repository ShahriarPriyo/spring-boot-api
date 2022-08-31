package com.springapi.Controllers;

import com.springapi.Entity.Users;
import com.springapi.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public ResponseEntity <Iterable<Users>> getAllUsers() {
        Iterable<Users> user = userServices.getAllUsers();
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(userServices.getAllUsers());
        }

    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Users>> getUser(@PathVariable int id) {
        Optional<Users> user = userServices.getUser(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(userServices.getUser(id));
        }

    }


    @PutMapping ( value = "/{id}")
    public ResponseEntity<Optional<Users>> updateUser( @PathVariable int id,@RequestBody Users user) {
        Optional<Users> updateUser = userServices.updateUser(id, user);
        if(updateUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        }

    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Optional<Users>> deleteUser(@PathVariable int id) {
      Optional<Users>deleteUser=  userServices.deleteUser(id);
      if(deleteUser.isEmpty()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }else {
          return ResponseEntity.status(HttpStatus.OK).body(deleteUser);
      }
    }
}
