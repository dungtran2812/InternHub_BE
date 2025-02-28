package com.kalocs.internhub.service;

import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.model.UserDTO;
import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.payload.request.signup.RecruiterSignupRequest;
import com.kalocs.internhub.payload.request.signup.StudentSignupRequest;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtResponseModel login(LoginRequest loginModel);

    StudentDTO studentSignup(StudentSignupRequest studentSignupRequest);

    RecruiterDTO recruiterSignup(RecruiterSignupRequest recruiterSignupRequest);

    UserDTO getInfoByToken();
}
