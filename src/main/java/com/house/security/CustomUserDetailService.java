package com.house.security;

import com.house.model.User;
import com.house.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoginAttemptService loginAttempService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String IP = getClientIP();
        if(loginAttempService.isBlocked(IP)) {
            throw new RuntimeException("Blocked");
        }else {
            User user = userRepository.findByEmail(s);
            if (user == null) {
                throw new UsernameNotFoundException("Email not found");
            } else {
                if(user.isDisabled()){
                    throw new RuntimeException("Disabled");
                }
                return new CustomUserDetails(user);
            }
        }
    }
    private String getClientIP(){
        String xfHeader = request.getHeader("X-Forwarded-For");
        if(xfHeader==null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
