package com.house.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        addCookie(getUserName(authentication),httpServletResponse);
        if(roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin/dashboard");
        }else if(roles.contains("ROLE_USER")){
            httpServletResponse.sendRedirect("/user/dashboard");
        }
    }
    private String getUserName(final Authentication authentication){
        return ((CustomUserDetails)authentication.getPrincipal()).getUsername();
    }

    private void addCookie(final String userName, final HttpServletResponse response){
        Cookie cookie = new Cookie("user",userName);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
