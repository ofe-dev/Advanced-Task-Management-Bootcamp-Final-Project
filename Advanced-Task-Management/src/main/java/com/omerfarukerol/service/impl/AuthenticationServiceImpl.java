package com.omerfarukerol.service.impl;


import com.omerfarukerol.entities.*;
import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;
import com.omerfarukerol.repository.UserRepository;
import com.omerfarukerol.security.JWTService;
import com.omerfarukerol.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Override
    public AuthResponseModel authenticate(AuthRequestModel request) {
        try {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
            String token = jwtService.generateToken(optionalUser.get());
            return new AuthResponseModel(token);
        }catch (Exception e){
            System.out.println("Kullanıcı adı veya şifre hatalı");
        }
        return null;
    }

}
