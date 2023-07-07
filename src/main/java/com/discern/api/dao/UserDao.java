package com.discern.api.dao;

import com.discern.api.model.User;
import com.discern.api.repository.UserRepository;
import com.discern.api.security.MyUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public UserDetails findByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            user = userRepository.findByUsername(email); // caso o receba o username inves do email
            //TODO - corrigir o
        }
        return new MyUserPrincipal(user);
    }

}
