package com.house.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        WebAuthenticationDetails authenticationDetails =
                (WebAuthenticationDetails) authenticationSuccessEvent.getAuthentication().getDetails();
        loginAttemptService.onLoginSuccedded(authenticationDetails.getRemoteAddress());
    }
}
