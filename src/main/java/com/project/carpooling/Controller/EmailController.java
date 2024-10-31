package com.project.carpooling.Controller;

import com.project.carpooling.Entity.Password;
import com.project.carpooling.Repository.PasswordRepo;
import com.project.carpooling.Servie.EmailServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/email")
public class EmailController
{

    @Autowired
    PasswordRepo repo;
    @Autowired
    EmailServie service;
    @GetMapping("/verify")
    public String getemail()
    {
        return "forgotpassword";
    }
    @PostMapping("/post")
    public String postemail(@RequestParam("email") String email, Model model)
    {
        int num=generate();
        service.sendEmail(email,"Password",num);
        Password obj1=new Password(email,num);
        repo.save(obj1);
        model.addAttribute("email",email);
        return "passwordchange";
    }
    public static int generate()
    {
        int num = new Random().nextInt(100, 200);
        return 100000+num;
    }
}
