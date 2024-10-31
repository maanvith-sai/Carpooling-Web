package com.project.carpooling.Servie;

import com.project.carpooling.Entity.User;
import com.project.carpooling.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class Userservice {
    @Autowired
    UserRepo repo;

    @Autowired
    BCryptPasswordEncoder encoder;

    public User saveuser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }
    public List<User> getalluser()
    {
        return repo.findAll();
    }
    public User getuser(String email)
    {
        return repo.findById(email).get();
    }

}
