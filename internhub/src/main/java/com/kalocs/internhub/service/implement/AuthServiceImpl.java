package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.repository.UserRepository;
import com.kalocs.internhub.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean signup(SignupModel signupModel) {
        try {
            User user = new User();
            user.setEmail(signupModel.getEmail());
            user.setUsername(signupModel.getEmail());
            user.setPassword(passwordEncoder.encode(signupModel.getPassword()));
            user.setFullName(signupModel.getFullName());
            user.setId(UUID.randomUUID());
            user.setRole("student");
            log.info(user);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error during create user " + signupModel.getFullName() + ": " +e);
            return false;
        }
    }
}
