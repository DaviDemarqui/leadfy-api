package com.discern.api.service;

import com.discern.api.model.User;
import com.discern.api.repository.UserRepository;
import com.discern.api.model.Profile;
import com.discern.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public User registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(user);
        profileRepository.save(profile);
        return user;
    }

}
