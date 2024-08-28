package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        //Authentication a = SecurityContextHolder.getContext().getAuthentication(); // второй вариант вытащить principal

        return "secured part of web service:" + principal.getName();
    }
}
