package com.dptsi.SpringBootRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseModel {
    @Column(name = "email", nullable = false)
    @Email(message = "Email is not valid")
    @Size(min = 5, max = 100)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 100)
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
