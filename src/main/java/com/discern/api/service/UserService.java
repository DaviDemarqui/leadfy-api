package com.discern.api.service;

import com.discern.api.dto.OwnerRegistrationDTO;
import com.discern.api.dto.ProfileDTO;
import com.discern.api.model.Company;
import com.discern.api.model.UserEntity;
import com.discern.api.repository.CompanyRepository;
import com.discern.api.repository.RoleRepository;
import com.discern.api.repository.UserRepository;
import com.discern.api.model.Profile;
import com.discern.api.repository.ProfileRepository;
import com.discern.api.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperUtil mapperUtil;

    public ProfileDTO registration(OwnerRegistrationDTO ownerRegistrationDTO) {

        UserEntity user = mapperUtil.mapTo(ownerRegistrationDTO.getUserDTO(), UserEntity.class);
        user.setPassword(passwordEncoder.encode(ownerRegistrationDTO.getUserDTO().getPassword()));
        user.setRoles(roleRepository.findAllByType("user"));
        userRepository.save(user); // Save the user

        Company company = mapperUtil.mapTo(ownerRegistrationDTO.getCompanyDTO(), Company.class);
        if(company.getCreatedOn() == null) { // Verifying the company date;
            company.setCreatedOn(LocalDateTime.now());
        }
        companyRepository.save(company); // Save the company

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setEmail(user.getEmail());
        profile.setName(user.getUsername());
        profile.setCompany_role("OWNER");
        profile.setCompanyId(company.getId());
        profile.setStatus(true);

        profileRepository.save(profile); // Save the profile

        // Return profileDTO
        return mapperUtil.mapTo(profile, ProfileDTO.class);
    }

}
