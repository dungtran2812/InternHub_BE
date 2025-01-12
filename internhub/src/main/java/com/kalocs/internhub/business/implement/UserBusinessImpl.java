package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.UserBusiness;
import com.kalocs.internhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBusinessImpl implements UserBusiness {
    @Autowired
    UserRepository userRepository;
}
