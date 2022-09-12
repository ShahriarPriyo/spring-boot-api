package com.springapi.Services;

import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import com.springapi.Utils.EntityNotFoundException;
import com.springapi.Utils.PasswordRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;
import java.util.Optional;

@Service
public class AuthServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users signUp(Users user){
        if(PasswordRegex.isValid(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }else {
//            throw new InvalidPropertiesFormatException(Users.class,
//                    "Password Not found",
//                    user.getPassword());
        }

        return user;
    }

    public Optional<Users> logIn(Users user){
        Optional<Users> authorizedUser= userRepository.findByEmail(user.getEmail());
        if(authorizedUser.isEmpty()) throw new EntityNotFoundException(Users.class,"Email", user.getEmail());
        if(passwordEncoder.matches(user.getPassword(),authorizedUser.get().getPassword())) return authorizedUser;
        return Optional.empty();
    }
}
