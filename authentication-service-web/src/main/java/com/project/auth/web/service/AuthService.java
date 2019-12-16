/*
 * 16/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.auth.web.service;

import com.project.auth.model.request.LoginRequest;
import com.project.auth.model.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService{

    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest loginRequest){

//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmailId(), loginRequest.getPassword())
//        );
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new LoginResponse();
    }
}
