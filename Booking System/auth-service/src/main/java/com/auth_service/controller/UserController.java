package com.auth_service.controller;


import com.auth_service.entity.LoginRequestDTO;
import com.auth_service.entity.LoginResponseDTO;
import com.auth_service.entity.UserRegistrationDTO;
import com.auth_service.entity.UserResponseDTO;
import com.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestBody UserRegistrationDTO userRegistrationDTO)
    {



        return new ResponseEntity<>(userService.registerUser(userRegistrationDTO), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO
    )
    {


        return new ResponseEntity<>(userService.login(loginRequestDTO), HttpStatus.OK);
    }







}
