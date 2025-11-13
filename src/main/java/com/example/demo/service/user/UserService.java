package com.example.demo.service.user;

import com.example.demo.controller.user.UserJoinForm;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity login(String loginId, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);

        if(optionalUser.isEmpty()) {
            return null;
        }

        UserEntity user = optionalUser.get();

        if(!user.password().equals(password)) {
            return null;
        }

        return user;
    }

    public boolean signup(UserJoinForm form){

        Optional<UserEntity> optionalUser = userRepository.findByLoginId(form.loginId());

        if(optionalUser.isPresent()) {
            return false;
        }

        userRepository.addUser(form.toEntity());
        return true;
    }

}
