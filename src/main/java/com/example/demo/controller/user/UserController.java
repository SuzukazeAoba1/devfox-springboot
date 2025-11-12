package com.example.demo.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
@RequestMapping("/")
public class UserController {

    @PostMapping("/login")
    public String login(@RequestParam String loginId,
                        @RequestParam String password){
        return "redirect:/tasks";
    }

    //会員登録form view
    @GetMapping("/signup")
    public String signup(UserJoinForm userJoinForm){
        return "signup";
    }

    //会員登録機能
    @PostMapping("/signup") //オーバーローディング
    public String signup(@Validated UserJoinForm userJoinForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        return "redirect:/";
    }

}
