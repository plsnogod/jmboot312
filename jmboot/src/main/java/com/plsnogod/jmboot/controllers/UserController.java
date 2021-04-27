package com.plsnogod.jmboot.controllers;


import com.plsnogod.jmboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public String getHomePage() {
        return "home_page";
    }

    @GetMapping("/user")
    public String clickMe(Model model, Principal principal) {
        model.addAttribute("oneUser", userServiceImpl.findByUserByEmail(principal.getName()));
        return "show_user";
    }
}
