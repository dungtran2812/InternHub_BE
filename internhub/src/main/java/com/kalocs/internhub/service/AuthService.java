package com.kalocs.internhub.service;

import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public boolean signup(SignupModel signupModel);
}
