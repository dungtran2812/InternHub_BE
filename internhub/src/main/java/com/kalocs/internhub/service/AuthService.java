package com.kalocs.internhub.service;

import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean signup(SignupModel signupModel);

    JwtResponseModel login(LoginRequest loginModel);
}
