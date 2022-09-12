package com.springapi.Services;

import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import com.springapi.Utils.EntityNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

        Optional<Users> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));
        }
        return Optional.of(user.get());
    }
    public Optional<Users> updateUser(int id, Users user){
        Optional<Users> updatedUser =  userRepository.findById(id);
        if(updatedUser.isEmpty()){
            throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));
        }
        setUserProperties(updatedUser.get(),user);
        userRepository.save(updatedUser.get());
        return updatedUser;
    }

    protected void setUserProperties(Users currentUser, Users previousUser){
        currentUser.setName(previousUser.getName());
        currentUser.setEmail(previousUser.getEmail());
        currentUser.setPassword(previousUser.getPassword());
        currentUser.setPhoneNumber(previousUser.getPhoneNumber());
    }

    public Optional<Users> deleteUser(int id){
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);

        }
        throw new EntityNotFoundException(Users.class,"id",String.valueOf(id));
    }

}
