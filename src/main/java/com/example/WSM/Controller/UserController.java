package com.example.WSM.Controller;

import com.example.WSM.Service.UserService;
import com.example.WSM.dto.LoginDTO;
import com.example.WSM.dto.LoginMessage;
import com.example.WSM.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody userDTO UserDTO)
    {
        String id = userService.addUser(UserDTO);
        return id;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginMessage loginMessage = (LoginMessage) userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
}}
