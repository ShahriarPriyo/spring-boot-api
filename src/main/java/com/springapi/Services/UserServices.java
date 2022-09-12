package com.springapi.Services;

import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices{
    @Autowired
    private UserRepository userRepository;

    public Iterable<Users> getAllUsers() {
        return userRepository.findAll();
        // return users;
    }

    public Optional<Users> getUser(int id) {
        return userRepository.findById(id);
    }
    public Optional<Users> updateUser(int id, Users user){
        Optional<Users> updatedUser =  userRepository.findById(id);
        if (updatedUser.isEmpty()){
            return Optional.empty();
        }
        if (!Strings.isBlank(user.getName())) {
            updatedUser.get().setName(user.getName());
        }
        if (!Strings.isBlank(user.getEmail())) {
            updatedUser.get().setEmail(user.getEmail());
        }
        if (!Strings.isBlank(user.getPassword())) {
            updatedUser.get().setPassword(user.getPassword());
        }
        if (!Strings.isBlank(user.getPhoneNumber())) {
            updatedUser.get().setPhoneNumber(user.getPhoneNumber());
        }
        userRepository.save(updatedUser.get());
        return updatedUser;
    }

    public Optional<Users> deleteUser(int id){
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return user;
        }
        return Optional.empty();
    }

}
