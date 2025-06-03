package com.auth_service.entity;

public record UserResponseDTO
        (
                 Long id,
                 String username,
                 String email,
                 String name
        ){
}
