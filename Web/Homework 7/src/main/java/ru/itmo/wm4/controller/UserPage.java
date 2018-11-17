package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wm4.service.UserService;

@Controller
public class UserPage extends Page{

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user/{id}")
    public String userGet(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userData", userService.findById(id));
        return "User";
    }

}
