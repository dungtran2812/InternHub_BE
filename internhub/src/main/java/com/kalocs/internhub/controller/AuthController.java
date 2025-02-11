package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.model.RecruiterDTO;
import com.kalocs.internhub.model.StudentDTO;
import com.kalocs.internhub.payload.request.LoginRequest;
import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.payload.request.signup.RecruiterSignupRequest;
import com.kalocs.internhub.payload.request.signup.StudentSignupRequest;
import com.kalocs.internhub.payload.response.JwtResponseModel;
import com.kalocs.internhub.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.AUTH)
@Log4j2
@CrossOrigin("*")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("student/signup")
    public ResponseEntity<StudentDTO> studentSignup(@Valid @RequestBody StudentSignupRequest studentSignupRequest) {
        log.info("studentSignup() AuthController start | {}", studentSignupRequest);
        StudentDTO result = authService.studentSignup(studentSignupRequest);
        log.info("studentSignup() AuthController end | {}", result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("recruiter/signup")
    public ResponseEntity<RecruiterDTO> recruiterSignup(@Valid @RequestBody RecruiterSignupRequest recruiterSignupRequest) {

        log.info("recruiterSignup() AuthController start | {}", recruiterSignupRequest);
        RecruiterDTO result = authService.recruiterSignup(recruiterSignupRequest);
        log.info("recruiterSignup() AuthController end | {}", result);
        return ResponseEntity.ok(result);

    }

    @PostMapping("login")
    public ResponseEntity<JwtResponseModel> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("login() AuthController start | {}", loginRequest);
        JwtResponseModel jwt = authService.login(loginRequest);
        log.info("login() AuthController end | {}", jwt);
        return ResponseEntity.ok(jwt);
    }


}
