package com.plsnogod.jmboot.controllers;


import com.plsnogod.jmboot.dao.UserRepository;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.service.UserService;
import com.plsnogod.jmboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;





@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



//    @GetMapping
//    public String getHomePage() {
//        return "home_page";
//    }
////
    @GetMapping
    public String clickMe() {
        return "page_user";
    }
//    @GetMapping
//    public String userInfo(){
//        return "page_user";
//    }
}
