package ru.example.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;
import ru.example.spring.services.BookService;
import ru.example.spring.services.PersonService;
import ru.example.spring.util.BookValidation;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {


    private BookService bookService;
    private PersonService personService;
    private BookValidation bookValidation;

    public BookController(BookService bookService, PersonService personService, BookValidation bookValidation) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidation = bookValidation;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("books",bookService.findAll());
        return "books/mainPageBook";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        bookValidation.validate(book,bindingResult);

        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("book",bookService.findOne(id));
        return "books/edit";
    }


    @GetMapping("/{id}")
    public String newPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person,
                            Model model) {

        model.addAttribute("book", bookService.findOne(id));

        Person person1 = personService.getOwnerBook(id);

        if (person1 != null)
            model.addAttribute("owner",person1);
        else
            model.addAttribute("people",personService.findAll());

        return "books/show";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id ,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        bookValidation.validate(book,bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(id,book);
        return "redirect:/books";
    }


    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id,
                              @ModelAttribute("book") Book book) {
        bookService.realese(id);
        return "redirect:/books/" + id;
    }


    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id ,
                              @ModelAttribute("person") Person person) {
        bookService.assign(person.getPersonId(),id);
        return "redirect:/books/" + id;
    }


    @DeleteMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
