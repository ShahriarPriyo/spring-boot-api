package com.springapi.JWTConfiguration;

import com.springapi.JWTUtils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthProvider {

    @Autowired
    private JWTUtility jwtUtility;


    @Autowired
    private UserDetailsServiceInfo userDetailsServiceInfo;

    public String provideToken(String email){

        final UserDetails userDetails=userDetailsServiceInfo.loadUserByUsername(email);
        final String token=jwtUtility.generateToken(userDetails);
        return token;
    }
}
