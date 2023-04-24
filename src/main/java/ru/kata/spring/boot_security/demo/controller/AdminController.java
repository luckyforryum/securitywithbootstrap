package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.UserDetailsImpl;

import java.util.List;

@Controller

public class AdminController {

    private final UserDetailsImpl userDetails;

    @Autowired
    public AdminController(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }


    @GetMapping("/")
    public String homepage() {

        return "index";
    }

    @GetMapping("/admin")
    public String pageForAdmin() {
        return "admin";
    }


    @GetMapping("/admin/showAllUsers")
    public String showAllUsers(Model model) {
        List<UserEntity> allUsers = userDetails.listAll();
        model.addAttribute("allUsers",allUsers);
        return "all-users";
    }

    @GetMapping("/admin/showAllUsers/addNewUser")
    public String addNewUser(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user",user);
        return "user-info";
    }

    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userDetails.save(user);

        return "redirect:/admin/showAllUsers";
    }


    @GetMapping("/admin/showAllUsers/update-info/{userId}")
    public String edit(@PathVariable("userId") Long id, Model model) {
        UserEntity user = userDetails.getUser(id);
        model.addAttribute("user1",user);
        return "/update-info";
    }

    @PatchMapping("/update-info1")
    public String update(@ModelAttribute("user1") UserEntity user) {
        userDetails.update(user);
        return "redirect:/admin/showAllUsers";
    }


    @DeleteMapping("/admin/showAllUsers/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        userDetails.delete(id);
        return "redirect:/admin/showAllUsers";
    }


}
