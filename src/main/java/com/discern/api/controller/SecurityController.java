package com.discern.api.controller;

import com.discern.api.dto.OwnerRegistrationDTO;
import com.discern.api.dto.TokenDTO;
import com.discern.api.model.Profile;
import com.discern.api.model.User;
import com.discern.api.repository.ProfileRepository;
import com.discern.api.repository.UserRepository;
import com.discern.api.service.UserService;
import com.discern.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody MultiValueMap<String, String> formData) {
        String email = UriUtils.decode(formData.getFirst("email"), StandardCharsets.UTF_8);
        String password = UriUtils.decode(formData.getFirst("password"), StandardCharsets.UTF_8);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (UsernameNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Profile profile = profileRepository.findProfileByEmail(email);

        if (profile != null) {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(jwtUtil.generateToken(profile.getCompanyId(), email, profile.getUser().getId()));
            return ResponseEntity.ok().body(tokenDTO);
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }


    @PostMapping("/create-account")
    public ResponseEntity<?> registrateUser(@RequestBody OwnerRegistrationDTO ownerRegistrationDTO) {
        return ResponseEntity.ok(userService.registration(ownerRegistrationDTO));
    }
}
