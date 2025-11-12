package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(){
        return "index";
    }

}
