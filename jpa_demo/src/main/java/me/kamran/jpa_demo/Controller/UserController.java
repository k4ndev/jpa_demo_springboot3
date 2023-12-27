package me.kamran.jpa_demo.Controller;

import me.kamran.jpa_demo.DTO.LogginMessage;
import me.kamran.jpa_demo.DTO.LoginDto;
import me.kamran.jpa_demo.DTO.LoginResponse;
import me.kamran.jpa_demo.DTO.UserDto;
import me.kamran.jpa_demo.Entity.User;
import me.kamran.jpa_demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Async
    public Future<User> Create(UserDto user){
        return userService.Create(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(LoginDto dto){
        LogginMessage result = userService.login(dto);
        return ResponseEntity.ok(result);
    }

}
