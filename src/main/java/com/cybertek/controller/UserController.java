package com.cybertek.controller;


import com.cybertek.dto.UserDTO;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @GetMapping({"/create", "/add", "/initialize"}) //it's possible to put several endpoints
    public String createUser(Model model){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());
        //model.addAttribute("users", userService.findAll());

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(UserDTO user, Model model){

        userService.save(user);
        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}") //different income request, that's why we put update
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("users", userService.listAllUsers()); //it will give the completed table, no update yet, just populating
        model.addAttribute("roles", roleService.listAllRoles());

        return "/user/update";
    }

    //will save edited info, or updated info
    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, UserDTO user, Model model){
        userService.update(user);
        return "redirect:/user/create";

    }

   @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, UserDTO user, Model model){
        userService.delete(username);
        return "redirect:/user/create";
    }

}
