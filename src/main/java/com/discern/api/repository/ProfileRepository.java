package com.discern.api.repository;

import com.discern.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @Author Davi Demarqui
 * @Created 20/jun./2023
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findProfileByEmail(String email);
}
