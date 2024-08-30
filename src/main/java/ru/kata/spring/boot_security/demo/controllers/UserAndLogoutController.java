package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import java.security.Principal;

@RestController
public class UserAndLogoutController {

    private UserDetailsServiceImpl userDetailsServiceImpl; // чтобы достать из Principal данные юзера

    @Autowired
    public void setUserService(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping("/user") // JSON(REST) страница отображения данных юзера, который вошел в систему
    public String pageForAuthenticatedUsers(Principal principal) { //principal это не Юзер
        //Authentication a = SecurityContextHolder.getContext().getAuthentication(); // второй вариант вытащить principal не передавая в параметры
        User user = userDetailsServiceImpl.findByUsername(principal.getName());
        return "You entered to secured part of web service<br> your name is: " + user.getUsername() + "<br>" +
                "your email: " + user.getUserEmail() + "<br>" +
                "your age: " + user.getUserAge() + "<br>" +
                "<a href='/logout'>Logout</a>";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "You have been successfully logged out." + "<br>" +
                "<a href='/login'>login</a>";
    }
}
