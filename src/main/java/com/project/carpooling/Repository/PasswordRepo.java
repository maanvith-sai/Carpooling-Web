package com.project.carpooling.Repository;

import com.project.carpooling.Entity.Password;
import com.project.carpooling.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepo extends JpaRepository<Password,String>
{

    User findByEmail(String email);


}
