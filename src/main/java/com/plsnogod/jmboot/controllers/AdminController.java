package com.plsnogod.jmboot.controllers;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.service.RoleService;
import com.plsnogod.jmboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String clickMe() {
        return "page_user";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model) {
        List<User> list_user = userService.showAllUsers();
        model.addAttribute("all_roles", roleService.list_roles());
        model.addAttribute("all_us", list_user);
        model.addAttribute("new_user", new User());
        model.addAttribute("all_roles", roleService.list_roles());
        return "table_users";
    }

//
//    @GetMapping("/add_user")
//    public String getUserForm(Model model) {
//
//        model.addAttribute("listRole", roleService.list_roles());
//        return "add_user";
//    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("new_user") User user,
                          @RequestParam(value = "select_role", required = false) String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                roleSet.add(roleService.list_roles().get(0));
            } else if (s.equals("ROLE_USER")) {
                roleSet.add(roleService.list_roles().get(1));
            }
        }

        user.setRolesSet(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/edit/{id}")
//    public String getUserFormUpdate(Model model, @PathVariable("id") long id) {
//        model.addAttribute("upd_user", userService.getUserById(id));
//        model.addAttribute("list_roles", roleService.list_roles());
//        return "update_user";
//    }

    @PostMapping(value = "/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "select_roles", required = false) String[] role) {
        userService.updateUser(user);
        return "redirect:/admin";

    }



//    private Set<Role> getAddRole(String[] array) {
//        HashSet<Role> hashSet = new HashSet<>();
//        for (int i = 0; i < array.length; i++) {
//            hashSet.add(roleService.findByRole(array[i]));
//        }
//        return hashSet;
//    }
    @PostMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}