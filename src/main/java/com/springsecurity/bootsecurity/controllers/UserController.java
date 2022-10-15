package com.springsecurity.bootsecurity.controllers;



import com.springsecurity.bootsecurity.model.User;
import com.springsecurity.bootsecurity.security.MyUserDetailes;
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


@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
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
        return "redirect:/user";
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

    @GetMapping("/user")
    public String showUserInfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetailes userDetales = (MyUserDetailes) authentication.getPrincipal();
        model.addAttribute("user", userDetales.getUser());
        System.out.println(userDetales.getUser());
        return "/user";

    }
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute("user") User user){
//        userService.add(user);
//        return "redirect:/";
//    }
    @GetMapping("/edit")
    public String editeUser(@RequestParam("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
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
