package com.example.demo.controller.user;

import com.example.demo.service.user.UserEntity;
import com.example.demo.service.user.UserRole;
import jakarta.validation.constraints.Size;

//front -> DB
public record UserJoinForm(
        @Size(min = 3, max = 20)
        String loginId,
        @Size(min = 8, max = 20)
        String password1,
        @Size(min = 8, max = 20)
        String password2,
        @Size(min = 3, max = 20)
        String nickname
) {

    // form 登録 -> Entity
    public UserEntity toEntity(String pass){
        return new UserEntity(null, loginId(), pass, nickname(), UserRole.USER, null);
    }

}
