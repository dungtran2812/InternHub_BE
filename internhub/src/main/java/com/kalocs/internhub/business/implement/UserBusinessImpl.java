package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.UserBusiness;
import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new BadCredentialsException("Wrong Password");
        }
        return user;
    }
}
