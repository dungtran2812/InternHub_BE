package com.kalocs.internhub.business;

import com.kalocs.internhub.entity.User;
import com.kalocs.internhub.payload.request.LoginRequest;

import java.util.Optional;

public interface UserBusiness {
    User getUserByEmail(String email);

    User login(LoginRequest loginRequest);
}
