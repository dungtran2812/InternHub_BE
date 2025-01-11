package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.payload.request.SignupModel;
import com.kalocs.internhub.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.AUTH)
@Log4j2
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupModel signupModel) {
        log.info("signup() AuthController start | " + signupModel);
        boolean result = authService.signup(signupModel);
        if (result) {
            log.info("signup() AuthController end | " + result);
            return ResponseEntity.ok().body("signup successful");
        } else {
            log.info("signup() AuthController end | " + result);
            return ResponseEntity.ok().body("signup failed");
        }
    }


}
