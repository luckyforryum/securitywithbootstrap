package ru.kata.spring.boot_security.demo.controller;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;


    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


}
