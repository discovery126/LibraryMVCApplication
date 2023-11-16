package ru.example.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.spring.dao.BookDao;
import ru.example.spring.models.Book;

@Component
public class BookValidation implements Validator {
    private BookDao bookDao;

    @Autowired
    public BookValidation(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDao.getBook(book.getNameBook()).isPresent()) {
            errors.rejectValue("nameBook","","Книга с таким названием уже существует");
        }
    }
}
