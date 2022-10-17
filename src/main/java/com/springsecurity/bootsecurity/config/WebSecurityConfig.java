package com.springsecurity.bootsecurity.config;

import com.springsecurity.bootsecurity.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final MyUserDetailService myUserDetailService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, MyUserDetailService myUserDetailService) {
        this.successUserHandler = successUserHandler;
        this.myUserDetailService = myUserDetailService;
    }


//    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService)
//                .passwordEncoder(getPasswordEncoder());
//
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        return daoAuthenticationProvider;
    }

        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/info").hasAnyRole("ADMIN", "USER")
                .antMatchers("/registration", "/login", "/error").permitAll()
                .antMatchers("/list").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()//.successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
