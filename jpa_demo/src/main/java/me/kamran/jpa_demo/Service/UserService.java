package me.kamran.jpa_demo.Service;

import me.kamran.jpa_demo.DTO.LogginMessage;
import me.kamran.jpa_demo.DTO.LoginDto;
import me.kamran.jpa_demo.DTO.UserDto;
import me.kamran.jpa_demo.Entity.User;
import me.kamran.jpa_demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncode;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Future<User> Create(UserDto user) {

        User u = new User();
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setPassword(this.passwordEncode.encode(user.getPassword()));

         return  (Future<User>) userRepository.save(u);
    }

    public LogginMessage login(LoginDto dto) {
        String msg = "";
        User employee1 = userRepository.findByEmail(dto.getEmail());
        if (employee1 != null) {
            String password = dto.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = this.passwordEncode.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(dto.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LogginMessage("Login Success", true);
                } else {
                     return new LogginMessage("Login Failed", false);
                }
            } else {
                 return new LogginMessage("password Not Match", false);
            }
        } else {
            return new LogginMessage("Email not exits", false);
        }

    }
}
