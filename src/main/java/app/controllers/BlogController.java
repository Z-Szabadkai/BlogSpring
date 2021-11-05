package app.controllers;

import app.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    private BlogService service;

    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }

    @PostMapping("/blogs")

    @GetMapping("/blogs")
}
