package me.kamran.jpa_demo.DTO;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
