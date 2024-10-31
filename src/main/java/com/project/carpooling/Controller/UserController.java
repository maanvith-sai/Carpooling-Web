package com.project.carpooling.Controller;

import com.project.carpooling.Entity.User;
import com.project.carpooling.Servie.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private Userservice userservice;

    @GetMapping("/")
    public String first()
    {
        return "Home";
    }

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("user",new User());
        return "userForm";
    }
    @GetMapping("/home")
    public String homepage()
    {
        return "success";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login"; // Thymeleaf template name
    }

    @GetMapping("/getAll")
    public String getAllUsers(Model model)
    {
        List<User> users=userservice.getalluser();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("/get/{email}")
    public String getUserById(@PathVariable String email, Model model) {
        User user = userservice.getuser(email);
        model.addAttribute("user", user);
        return "userDetail";
    }
    @GetMapping("/create")
    public String createUserForm(Model model)
    {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/save")
    public String User(@ModelAttribute("user") User user)
    {
        System.out.print("hello");
        userservice.saveuser(user);
        return "redirect:/getAll";
    }
}
