package app.controllers;

import app.models.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/register")
    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")

    @GetMapping("/users/{id}")

    @GetMapping("/user")


}
