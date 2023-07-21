package com.discern.api.controller;

import com.discern.api.dto.AuthResponseDTO;
import com.discern.api.dto.OwnerRegistrationDTO;
import com.discern.api.dto.TokenDTO;
import com.discern.api.model.Profile;
import com.discern.api.model.UserEntity;
import com.discern.api.repository.ProfileRepository;
import com.discern.api.repository.UserRepository;
import com.discern.api.security.JwtGenerator;
import com.discern.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody MultiValueMap<String, String> formData) {
        String email = UriUtils.decode(formData.getFirst("email"), StandardCharsets.UTF_8);
        String password = UriUtils.decode(formData.getFirst("password"), StandardCharsets.UTF_8);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Profile profile = profileRepository.findProfileByEmail(email);
        UserEntity user = userRepository.findByEmail(email);
        String token = jwtGenerator.generateToken(profile.getCompanyId(), email, profile.getId(), user);

        return ResponseEntity.accepted().body(new AuthResponseDTO(token, "Bearer "));
    }


    @PostMapping("/create-account")
    public ResponseEntity<?> registrateUser(@RequestBody OwnerRegistrationDTO ownerRegistrationDTO) {
        return ResponseEntity.ok(userService.registration(ownerRegistrationDTO));
    }
}
