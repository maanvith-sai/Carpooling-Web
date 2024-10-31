package com.project.carpooling.Controller;

import com.project.carpooling.Entity.Password;
import com.project.carpooling.Entity.User;
import com.project.carpooling.Repository.PasswordRepo;
import com.project.carpooling.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordRepo repo;

    @Autowired
    UserRepo repo1;
    @Autowired
    private BCryptPasswordEncoder encode;

    User obj;


//    @GetMapping("/reset")
//    public String reset()
//    {
//        return "passwordchange";
//    }
    @PostMapping("/change")
    public String postdata(@RequestParam("email") String email, @RequestParam("code") Integer code, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,Model model)
    {
        System.out.println(email);
        obj=repo1.findByEmail(email);

        if (!newPassword.equals(confirmPassword))
        {
            model.addAttribute("email",email);
            model.addAttribute("error", "Passwords do not match.");
            return "passwordchange";
        }

        Password passwordEntry = repo.findById(email).orElse(null);
        if (passwordEntry == null || !passwordEntry.getCode().equals(code))
        {
            model.addAttribute("email",email);
            model.addAttribute("error", "Invalid email or code.");
            return "passwordchange";
        }
        System.out.print(newPassword+" "+confirmPassword);

        User obj1=new User();
        obj1.setPassword(encode.encode(newPassword));
        obj1.setRole(obj.getRole());
        obj1.setEmail(obj.getEmail());
        obj1.setUsername(obj.getUsername());

        repo1.deleteById(obj.getEmail());
        repo1.save(obj1);
        return "redirect:/user/login";
    }
}