package com.example.ost.controller;

import com.example.ost.service.SpotifyAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    private final SpotifyAuthService authService;

    public AuthController(SpotifyAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth/login")
    public String login() {
        String url = authService.loginUrl();
        return "redirect:" + url;
    }

    @GetMapping("/auth/callback")
    @ResponseBody
    public String callback(@RequestParam String code) {
        System.out.println(">>> /auth/callback called, code = " + code);

        authService.requestToken(code);

        System.out.println(">>> /auth/callback finished");

        return "Success! Spotify Login Completed";
    }


}
