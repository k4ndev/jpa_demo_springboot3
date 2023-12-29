package me.kamran.jpa_demo.Service;

import lombok.RequiredArgsConstructor;
import me.kamran.jpa_demo.DTO.LogginMessage;
import me.kamran.jpa_demo.DTO.LoginDto;
import me.kamran.jpa_demo.DTO.LoginResponse;
import me.kamran.jpa_demo.DTO.UserDto;
import me.kamran.jpa_demo.Entity.Role;
import me.kamran.jpa_demo.Entity.User;
import me.kamran.jpa_demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncode;

    public String Save(UserDto dto){
        var user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .surname(dto.getSurname())
                .password(passwordEncode.encode(dto.getPassword()))
                .role(Role.User)
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public LogginMessage login(LoginDto dto) {
        String msg = "";
        Optional<User> employee1 = userRepository.findByEmail(dto.getEmail());
        if (employee1.isPresent()) {
            String password = dto.getPassword();
            String encodedPassword = employee1.get().getPassword();
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

    public LoginResponse auth(LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),dto.getPassword()
                )
        );
        var user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        return LoginResponse.builder().Token(token).status(true).build();

    }
}
