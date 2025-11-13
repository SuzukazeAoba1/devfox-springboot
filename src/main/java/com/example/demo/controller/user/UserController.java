package com.example.demo.controller.user;

import com.example.demo.service.user.UserEntity;
import com.example.demo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String loginId, //RequestParam 活用練習
                        @RequestParam String password,
                        HttpServletRequest request){

        if(loginId.isBlank() || password.isBlank()) {
            return "redirect:/";
        }

        UserEntity user = userService.login(loginId, password);

        if(user == null) {
            return "redirect:/";
        }

        // session init
        request.getSession().invalidate();

        // make session
        HttpSession session = request.getSession(true);
        session.setAttribute("loginId", user.loginId());
        session.setAttribute("nickname", user.nickname());
        session.setAttribute("role", user.role());

        // session timeout 30分
        session.setMaxInactiveInterval(1800);

        return "redirect:/tasks";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    //会員登録form view
    @GetMapping("/signup")
    public String signup(UserJoinForm userJoinForm){
        return "signup";
    }

    //会員登録
    @PostMapping("/signup") //オーバーローディング
    public String signup(@Validated UserJoinForm userJoinForm,
                         BindingResult bindingResult,
                         Model model){

        //UIチェックはcontrollerで
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        //UIチェックはcontrollerで
        if(!userJoinForm.password1().equals(userJoinForm.password2()))
        {
            model.addAttribute("passwordError", "パスワードが間違っています。");
            return "signup";
        }

        //DBチェックはserviceで
        if(!userService.signup(userJoinForm))
        {
            model.addAttribute("idError", "同じIDがあります。");
            return "signup";
        }

        return "redirect:/";
    }

}
