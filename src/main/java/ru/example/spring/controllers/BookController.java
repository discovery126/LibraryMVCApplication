package ru.example.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.spring.dao.BookDao;
import ru.example.spring.dao.PersonDao;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;
import ru.example.spring.util.BookValidation;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDao bookDao;
    private PersonDao personDao;
    private BookValidation bookValidation;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao,BookValidation bookValidation) {
        this.bookDao = bookDao;
        this.personDao = personDao;
        this.bookValidation = bookValidation;
    }

    @GetMapping()
    public String mainPage(Model model) {
        model.addAttribute("books",bookDao.getAllBook());
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

        bookDao.create(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("book",bookDao.getBook(id));
        return "books/edit";
    }


    @GetMapping("/{id}")
    public String newPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person,
                            Model model) {

        model.addAttribute("book",bookDao.getBook(id));

        Optional<Person> person1 = bookDao.getOwnerBook(id);

        if (person1.isPresent())
            model.addAttribute("owner",person1.get());
        else
            model.addAttribute("people",personDao.getAllPerson());

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

        bookDao.updateBook(book,id);
        return "redirect:/books";
    }


    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id,
                              @ModelAttribute("book") Book book) {
        bookDao.release(id);
        return "redirect:/books/" + id;
    }


    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id ,
                              @ModelAttribute("person") Person person) {
        bookDao.assign(person.getPersonId(),id);
        return "redirect:/books/" + id;
    }


    @DeleteMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}
