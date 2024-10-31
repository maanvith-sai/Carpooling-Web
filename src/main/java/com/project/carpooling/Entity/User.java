package com.project.carpooling.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name="users")
@Data
public class User {
    @Id
    private String email;
private String username;
private String password;
private String role;
}
