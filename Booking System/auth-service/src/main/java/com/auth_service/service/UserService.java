package com.auth_service.service;

import com.auth_service.entity.*;
import com.auth_service.repository.UserRepository;
import com.auth_service.tools.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private  AuthenticationManager authenticationManager;


    @Autowired
    JwtService jwtService;


    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {

        Optional<User> existingUser = userRepository.findByEmail(userRegistrationDTO.email());

        if(existingUser.isPresent())
        {
            throw new RuntimeException("Korisnik sa email: "+ " vec postoji");
        }


        User newUser = new User();
        newUser.setEmail(userRegistrationDTO.email());
        newUser.setName(userRegistrationDTO.name());
        newUser.setEnabled(true);
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));

        User savedUser = userRepository.save(newUser);

        return userMapper.entitiyToResponse(savedUser);

    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.password()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token, userDetails.getUsername(), userDetails.getRole());
    }
}
