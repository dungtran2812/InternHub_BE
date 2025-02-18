package com.kalocs.internhub.config.security.oauth2.user;

import com.kalocs.internhub.common.AuthProvider;
import com.kalocs.internhub.config.handler.AppException;

import java.util.Map;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new AppException(405,"Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}