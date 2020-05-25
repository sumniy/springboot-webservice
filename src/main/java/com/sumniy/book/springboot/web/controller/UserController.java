package com.sumniy.book.springboot.web.controller;

import com.sumniy.book.springboot.config.auth.LoginUser;
import com.sumniy.book.springboot.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(Model model, @LoginUser SessionUser sessionUser) {

        model.addAttribute("userName", sessionUser.getName());
        model.addAttribute("userEmail", sessionUser.getEmail());
        model.addAttribute("userPicture", sessionUser.getPicture());

        return "user";
    }

}
