package ru.example.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.example.spring.models.Person;
import ru.example.spring.services.BookService;
import ru.example.spring.services.PersonService;
import ru.example.spring.util.PersonValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final BookService bookService;
    private final PersonValidator personValidator;

    public PersonController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("people",personService.findAll());

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

        personService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}")
    public String newPerson(@PathVariable("id") int id,
                            Model model) {
        model.addAttribute("person",personService.findOne(id));
        model.addAttribute("books",bookService.getOwnerBook(id));
        return "people/show";
    }


    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("person",personService.findOne(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id ,
                         @ModelAttribute("person") Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        personService.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
