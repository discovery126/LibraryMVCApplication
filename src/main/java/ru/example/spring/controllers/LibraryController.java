package ru.example.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {

    @GetMapping("/mainPage")
    public String dispayMainPage() {
        return "mainPage";
    }
}
