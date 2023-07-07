package com.discern.api.service;

import com.discern.api.model.Profile;
import com.discern.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Optional<Profile> findUserProfile(Long idProfile) {
        Optional<Profile> profile = profileRepository.findById(idProfile);
        return profile;
    }
}
