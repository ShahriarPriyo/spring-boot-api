package com.springapi.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    public String currentUser(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}
