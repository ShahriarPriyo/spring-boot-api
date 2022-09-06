package com.springapi.Services;

import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServices {
    @Autowired
    private UserRepository userRepository;

    public Users signUp(Users user){
        return userRepository.save(user);
    }

    public Optional<Users> logIn(Users user){
        Optional<Users> authorizedUser= userRepository.findByEmail(user.getEmail());
        if(authorizedUser.isEmpty())
            return Optional.empty();
        if(authorizedUser.get().getPassword().equals(user.getPassword()))
            return authorizedUser;

        return Optional.empty();
    }
}
