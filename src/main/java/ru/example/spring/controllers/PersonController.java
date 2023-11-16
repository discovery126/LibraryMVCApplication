package ru.example.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.spring.dao.BookDao;
import ru.example.spring.dao.PersonDao;
import ru.example.spring.models.Person;
import ru.example.spring.util.PersonValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    private final BookDao bookDao;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDao personDao, BookDao bookDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.bookDao = bookDao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("people",personDao.getAllPerson());

        return "people/mainPagePerson";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDao.create(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}")
    public String newPerson(@PathVariable("id") int id,
                            Model model) {
        model.addAttribute("person",personDao.getPerson(id));
        model.addAttribute("books",bookDao.getBookPerson(id));
        return "people/show";
    }


    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("person",personDao.getPerson(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id ,
                         @ModelAttribute("person") Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        personDao.update(person,id);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }
}
