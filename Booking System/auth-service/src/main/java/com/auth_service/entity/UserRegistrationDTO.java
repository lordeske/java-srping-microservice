package com.auth_service.entity;


import lombok.Builder;


@Builder
public record UserRegistrationDTO(



         String email,
         String password,
         String name




        ){







}
