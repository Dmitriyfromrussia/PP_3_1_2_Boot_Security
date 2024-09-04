package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all-users")
    public String allUsersPage(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("userList", users);
        return "admin/all-users";
    }

    @GetMapping("/edit")
    public String receiveUserForUpdate(@RequestParam("id") long id, Model model) {

        User userForUpdate = userService.getById(id);
        model.addAttribute("userForEdit", userForUpdate);

        Set<Role> allRoles = roleService.findAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/edit-user";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("userForEdit") @Valid User editUser,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-user"; // Вернуть на страницу редактирования, если есть ошибки
        }
        userService.update(editUser);
        return "redirect:/admin/all-users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        User user = userService.getById(id);
        user.setRoles(new HashSet<>());
        userService.delete(id);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        User emptyUser = new User();
        model.addAttribute("emptyUser", emptyUser);
        return "admin/add-user";
    }

//    @PostMapping("/add")
//    public String addUser (@RequestParam )
}
