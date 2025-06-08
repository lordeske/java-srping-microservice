package com.auth_service.tools;


import com.auth_service.entity.User;
import com.auth_service.entity.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {



    public UserResponseDTO entitiyToResponse(User user)
    {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName()
        );

    }



}
