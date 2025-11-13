package com.example.demo.service.user;

import java.sql.Timestamp;

public record UserEntity(
        Long id,
        String loginId,
        String password,
        String nickname,
        UserRole role,
        Timestamp created_at
) {



}
