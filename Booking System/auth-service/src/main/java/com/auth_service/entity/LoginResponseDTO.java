package com.auth_service.entity;

public record LoginResponseDTO (

        String token,
        String username,
        Role role
){

}
