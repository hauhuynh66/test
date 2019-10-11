package com.house.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Locale locale = localeResolver.resolveLocale(httpServletRequest);
        String errorMessage = messageSource.getMessage("message.auth.badCredentials",null,locale);
        System.out.println(errorMessage);
        if(e.getMessage().equalsIgnoreCase("blocked")){
            httpServletResponse.sendRedirect("/login?blocked");
        }else if(e.getMessage().equalsIgnoreCase("disabled")) {
            httpServletResponse.sendRedirect("/login?disabled");
        }else{
            httpServletResponse.sendRedirect("/login?error");
        }
    }
}
