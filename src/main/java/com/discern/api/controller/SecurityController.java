package com.discern.api.controller;

import com.discern.api.model.User;
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
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody MultiValueMap<String, String> formData) {
        String email = UriUtils.decode(formData.getFirst("email"), StandardCharsets.UTF_8);
        String password = UriUtils.decode(formData.getFirst("password"), StandardCharsets.UTF_8);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (UsernameNotFoundException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails != null) {
            String token = jwtUtil.generateToken(userDetails);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            return ResponseEntity.ok().headers(headers).build();
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registrateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registration(user));
    }
}
