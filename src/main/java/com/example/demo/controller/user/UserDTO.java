package com.example.demo.controller.user;

import com.example.demo.service.user.UserEntity;

import java.sql.Timestamp;

//DB -> front
public record UserDTO(
        Long id,
        String loginId,
        String password,
        String nickname,
        String role,
        Timestamp created_at
) {

    // Entity -> DTO
    public static UserDTO toDTO(UserEntity entity){
        return new UserDTO(
                entity.id(),
                entity.loginId(),
                entity.password(),
                entity.nickname(),
                entity.role().name(),
                entity.created_at()
        );
    }
}
