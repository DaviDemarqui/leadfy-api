package com.discern.api.utils;

import com.discern.api.model.User;
import com.discern.api.repository.UserRepository;
import com.discern.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserTokenValidation {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public User findUserByToken(String token) {
        User user = userRepository.findByEmail(jwtUtil.extractUsername(token));
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
