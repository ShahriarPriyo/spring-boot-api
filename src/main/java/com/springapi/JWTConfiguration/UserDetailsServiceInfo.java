package com.springapi.JWTConfiguration;
import com.springapi.Entity.Users;
import com.springapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserDetailsServiceInfo implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Users>newUser=userRepository.findByEmail(email);
        return new User(newUser.get().getEmail(),newUser.get().getPassword(),new ArrayList<>());
    }
}
