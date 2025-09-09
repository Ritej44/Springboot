package com.example.WSM.Service;

import com.example.WSM.Model.User;
import com.example.WSM.Repository.UserRepository;
import com.example.WSM.dto.LoginDTO;
import com.example.WSM.dto.LoginMessage;
import com.example.WSM.dto.userDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String addUser(userDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user);
        return user.getName();
    }
    userDTO userDTO;


    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) { // Utilise `LoginMessage` et non `LoginMesage`
        String msg = "";
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true); // Utilise `LoginMessage`
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        } else {
            return new LoginMessage("Email not exists", false);
        }
    }
}