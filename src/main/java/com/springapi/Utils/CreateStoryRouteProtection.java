package com.springapi.Utils;

import com.springapi.Entity.Story;
import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateStoryRouteProtection {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthProvider authProvider;

    public Users checkUserValidation(){
        String userEmail=authProvider.currentUser();
        System.out.println(userEmail);
        Optional<Users> users = userRepository.findByEmail(userEmail);

        System.out.println(users.get());
        if(users.isEmpty())
            throw new EntityNotFoundException(Story.class,"Email",userEmail);
        return users.get();
    }
}
