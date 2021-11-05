package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticController {

    @GetMapping(value = {"/", "/home"})
    public String getHomePage () {
        return "index";
    }
}
