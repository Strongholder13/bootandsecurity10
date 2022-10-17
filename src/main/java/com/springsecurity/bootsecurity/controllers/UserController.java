package com.springsecurity.bootsecurity.controllers;



import com.springsecurity.bootsecurity.model.User;


import com.springsecurity.bootsecurity.service.MyUserDetailService;
import com.springsecurity.bootsecurity.service.UserService;
import com.springsecurity.bootsecurity.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    //private final MyUserDetailService detailsServiceService ;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
       // this.detailsServiceService = detailsServiceService;
        this.userValidator = userValidator;
    }

    private final UserValidator userValidator;


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "/admin";
    }
//    @GetMapping("/new")
//    public String newUser(Model model){
//    User user = new User();
//    model.addAttribute("user", user);
//        return "/info";
//    }

//    @GetMapping("/user")
//    public String showUser(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        MyUserDetailes myDetails = (MyUserDetailes) auth.getPrincipal();
//        model.addAttribute("user", myDetails.getUser());
//
//        return "Hello";
//    }

@GetMapping("/info")
public String showUserInfo(Model model, Principal principal) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    System.out.println(principal.getName());
    System.out.println(authentication.getDetails());
    //System.out.println(authentication.getPrincipal());
    System.out.println(authentication.getCredentials());
    System.out.println(authentication.getClass());
    model.addAttribute("user", userService.findByUsername(principal.getName()));

//    model.addAttribute("user", userService.findByUsername(authentication.getName()));
//    model.addAttribute("roles", userService.listRoles(authentication.getName()));
    //System.out.println(authentication.getAuthorities());


//model.addAttribute("user", userDetales.getUser());
//    String username = authentication.getName();
    //User user = userService.findByUsername(userDetailes);

     //model.addAttribute("user", userService.findById(id));
     //model.addAttribute("role", user.getRoles());
    return "Hello";
}
//    @GetMapping("/user")
//    public String showUserInfo(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        User user = userService.findByUsername(authentication.getName());
//        System.out.println(authentication.getName());
//       // model.addAttribute("user", user);
//
//
//        //System.out.println(authentication.getPrincipal());
//        //System.out.println(authentication.getName());
//
//       // model.addAttribute("user", userDetales);
//        return "/admin";
//
//    }
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("user") User user){
//        userService.add(user);
//        return "redirect:/";
//    }
    @GetMapping("/edit")
    public String editeUser(@RequestParam("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        System.out.println(user.getUsername());
        return "/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin";
    }
    @PostMapping ("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
