package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.model.CustomUserDetails;
import com.dryfruits.dryfruitsbackend.model.User;
import com.dryfruits.dryfruitsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNo(phoneNo);

        if (user == null) {
            user = userRepository.findByPhoneNo(phoneNo);
        } else {
            return new CustomUserDetails(
                    user.getId(),
                    user.getPhoneNo(),
                    false, false, false, false
            );
        }

        return new CustomUserDetails(
                user.getId(),
                user.getPhoneNo(),
                true, true, true, true
        );
    }
}
