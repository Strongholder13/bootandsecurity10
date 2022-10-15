package com.springsecurity.bootsecurity.controllers;


import com.springsecurity.bootsecurity.security.MyUserDetailes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String SayHello() {
        return "Hello";
    }

//    @GetMapping("/info")
//    public String showUserInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        MyUserDetailes userDetailes = (MyUserDetailes) authentication.getPrincipal();
//        System.out.println(userDetailes.getUser());
//        return "Hello";


}
