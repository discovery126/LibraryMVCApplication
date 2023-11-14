package ru.example.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.spring.dao.PersonDao;

@Controller
@RequestMapping("/people")
public class LibraryController {

    PersonDao personDao;

    @Autowired
    public LibraryController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("people",personDao.getAllPerson());
        return "people/mainPage";
    }
}
