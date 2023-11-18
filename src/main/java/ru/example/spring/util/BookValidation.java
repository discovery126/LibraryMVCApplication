package ru.example.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;
import ru.example.spring.services.BookService;

@Component
public class BookValidation implements Validator {
    private BookService bookService;

    @Autowired
    public BookValidation(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (bookService.findOne(book.getNameBook())!=null) {
            errors.rejectValue("nameBook", "", "Книга с таким названием уже существует");
        }
    }
}
