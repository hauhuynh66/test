package com.house.security;

import com.house.model.User;
import com.sun.deploy.net.HttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin/dashboard");
        }else if(roles.contains("ROLE_USER")){
            httpServletResponse.sendRedirect("/user/dashboard");
        }
    }
    private String getUserName(final Authentication authentication){
        return ((User)authentication.getPrincipal()).getName();
    }
    private void addCookie(final String userName, final HttpServletResponse response){
        Cookie cookie = getCookie(userName);
        response.addCookie(cookie);
    }
    private Cookie getCookie(final String userName){
        Cookie cookie = new Cookie("welcome",userName);
        cookie.setMaxAge(60*60*24);
        return cookie;
    }
    protected void clearAuthenticationAttributes(final HttpServletRequest request){
        final HttpSession session = request.getSession(false);
        if(session==null){
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
