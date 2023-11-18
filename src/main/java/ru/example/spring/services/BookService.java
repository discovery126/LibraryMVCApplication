package ru.example.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;
import ru.example.spring.repositories.BookRepository;
import ru.example.spring.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;


    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }
    public Book findOne(String nameBook) {
       return bookRepository.findByNameBook(nameBook);
    }
    public List<Book> getOwnerBook(int personId) {
        return bookRepository.getBookPerson(personId);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book updatedBook) {
        bookRepository.save(updatedBook);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void realese(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book;
        if (bookOptional.isPresent()) {
            book = bookOptional.get();
            book.setOwner(null);
            bookRepository.save(book);
            return;
        }
        System.out.println("realese method ERROR");

    }
    @Transactional
    public void assign(int personId, int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Person> personOptional = personRepository.findById(personId);
        Book book;
        Person person;
        if (bookOptional.isPresent() && personOptional.isPresent()) {
            book = bookOptional.get();
            person = personOptional.get();
            book.setOwner(person);
            bookRepository.save(book);
            return;
        }
        System.out.println("assign method ERROR");
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

}
