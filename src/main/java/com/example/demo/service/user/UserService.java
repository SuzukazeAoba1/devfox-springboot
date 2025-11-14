package com.example.demo.service.user;

import com.example.demo.controller.user.UserJoinForm;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        if(!matchesBcrypt(password, user.password(),10)) {
            return null;
        }

        return user;
    }

    public boolean signup(UserJoinForm form){

        Optional<UserEntity> optionalUser = userRepository.findByLoginId(form.loginId());

        if(optionalUser.isPresent()) {
            return false;
        }

        var pass = encodeBcrypt(form.password1(),10);
        userRepository.addUser(form.toEntity(pass));
        return true;
    }

    // https://bbubbush.tistory.com/36#google_vignette
    public String encodeBcrypt(String planeText, int strength) {
        return new BCryptPasswordEncoder(strength).encode(planeText);
    }

    public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
        return passwordEncoder.matches(planeText, hashValue);
    }

}
