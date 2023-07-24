package com.discern.api.controller;

import com.discern.api.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Davi Demarqui
 * @Created 20/jun./2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@PreAuthorize("hasRole('ROLE_PROFILE')")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("{id}")
    public ResponseEntity<?> findProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.findUserProfile(id));
    }
}
