package com.house.config;

import com.house.security.CustomAuthenticationSuccessHandler;
import com.house.security.CustomAuthenticationFailureHandler;
import com.house.security.CustomUserDetailService;
import com.house.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Utils utils;
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").failureHandler(failureHandler).successHandler(successHandler).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/forbidden")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID","user")
                .and()
                .rememberMe().key("unique").tokenValiditySeconds(36000)
                .userDetailsService(userDetailService)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(utils.passwordEncoder());
    }
}
