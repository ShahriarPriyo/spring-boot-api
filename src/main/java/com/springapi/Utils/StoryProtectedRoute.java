package com.springapi.Utils;

import com.springapi.Entity.Story;
import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class StoryProtectedRoute {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthProvider authProvider;

    public void checkValidUser(Integer authorId){
        String userEmail = authProvider.currentUser();
        Optional<Users> user = userRepository.findByEmail(userEmail);
        if(user.isEmpty())
            throw new EntityNotFoundException(Story.class,"id",String.valueOf(authorId));
        Integer userId= user.get().getId();
        if(!(authorId.equals(userId)))
            throw new RuntimeException();

    }
}
