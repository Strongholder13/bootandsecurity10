package com.springsecurity.bootsecurity.util;

import com.springsecurity.bootsecurity.model.User;
import com.springsecurity.bootsecurity.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {

    private final MyUserDetailService myUserDetailService;

    @Autowired
    public UserValidator(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    User user = (User) o;
    try {
        myUserDetailService.loadUserByUsername(user.getUsername());
    } catch (UsernameNotFoundException ignored) {
        return;
    }
    errors.rejectValue("username","" ,"Username уже существует");
    }
}
