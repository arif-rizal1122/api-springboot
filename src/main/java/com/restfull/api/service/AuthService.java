package com.restfull.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.restfull.api.entity.User;
import com.restfull.api.model.LoginUserRequest;
import com.restfull.api.model.TokenResponse;
import com.restfull.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AuthService {
    @Autowired   
    private UserRepository userRepository;
 
    @Autowired
    private ValidationService validationService;

    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong"));
   
        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next300Days());
            userRepository.save(user);

            return TokenResponse.builder()
            .token(user.getToken())
            .expiredAt(user.getTokenExpiredAt())
            .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");
        }
    }


    private Long next300Days(){
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }


    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }
    

}
