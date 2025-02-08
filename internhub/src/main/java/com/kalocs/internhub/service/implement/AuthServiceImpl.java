package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.UserBusiness;
import com.kalocs.internhub.common.UserRole;
import com.kalocs.internhub.config.security.services.UserDetailsImpl;
import com.kalocs.internhub.entity.Student;
import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import com.kalocs.internhub.repository.UserRepository;
import com.kalocs.internhub.config.security.jwt.JwtUtils;
import com.kalocs.internhub.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
@Log4j2
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserBusiness userBusiness;
    @Autowired
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserBusiness userBusiness,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userBusiness = userBusiness;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean signup(SignupModel signupModel) {
        try {
            User user = new Student();
            user.setEmail(signupModel.getEmail());
            user.setUsername(signupModel.getEmail());
            user.setPassword(passwordEncoder.encode(signupModel.getPassword()));
            user.setFullName(signupModel.getFullName());
            user.setId(UUID.randomUUID());
            user.setRole(UserRole.STUDENT);
            log.info(user);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error during create user {}: {}", signupModel.getFullName(), e);
            return false;
        }
    }

    @Override
    public JwtResponseModel login(LoginRequest loginRequest) {
        try {
            log.info("login() AuthServiceImpl Start | {}", loginRequest);
            User user = userBusiness.getUserByEmail(loginRequest.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new JwtResponseModel(jwt, "Bearer", userDetails.getId(), userDetails.getUsername(), loginRequest.getEmail(), userDetails.getRole());
        } catch (Exception ex) {
            log.error("login() AuthServiceImpl Error | {}: {}", loginRequest.getEmail(), ex.getMessage());
            throw ex;
        }

    }
}
