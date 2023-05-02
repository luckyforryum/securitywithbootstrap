package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;


@Controller
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }




    @GetMapping("/admin/showAllUsers")
    public String showAllUsers(Principal principal,Model model) {
        List<UserEntity> allUsers = userService.getAllUsers();
        String email = principal.getName();
        UserEntity userEntity = userService.getInfoByEmail(email);
        model.addAttribute("email",email);
        model.addAttribute("roles",userEntity.getRoles());
        model.addAttribute("allRoles",roleService.getAllURoles());
        model.addAttribute("allUsers",allUsers);
        model.addAttribute("thisUser",userEntity);
        return "all-users";
    }

//    @GetMapping("/admin/showAllUsers/addNewUser")
//    public String addNewUser(Model model) {
//        UserEntity user = new UserEntity();
//        model.addAttribute("user",user);
//        return "user-info";
//    }


    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute UserEntity user) {
        userService.save(user);
        return "redirect:/admin/showAllUsers";
    }


    @PostMapping("/admin/showAllUsers/update-info")
    public String edit(@ModelAttribute UserEntity editUser) {
        userService.update(editUser);
        return "redirect:/admin/showAllUsers";
    }

    @PostMapping("/admin/showAllUsers/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.delete(id);
        return "redirect:/admin/showAllUsers";
    }
}
