package com.example.WSM.Service;

import com.example.WSM.dto.LoginDTO;

import com.example.WSM.dto.userDTO;
;

public interface UserService<LoginMessage> {

     String addUser(userDTO userDTODTO);
    LoginMessage loginUser(LoginDTO loginDTO);
}
