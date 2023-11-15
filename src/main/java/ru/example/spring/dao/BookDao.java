package ru.example.spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BookDao {
    JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBook() {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY book_id", new BookMapper());
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO book(name_book, author_book,date_publication) VALUES(?, ?, ?)",
                book.getNameBook(),
                book.getAuthorBook(),
                book.getDatePublication()
        );
    }
    public Book getBook(int id) {
        List<Book> bookList = jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id},
                new BookMapper());
        return bookList.stream().findAny().orElse(null);
    }
    public void updateBook(Book updatedBook, int id) {
        jdbcTemplate.update("UPDATE book SET name_book=?,author_book=?, date_publication=? WHERE book_id= ?",
                updatedBook.getNameBook(),
                updatedBook.getAuthorBook(),
                updatedBook.getDatePublication(),
                id);
    }
    public Optional<Person> getOwnerBook(int id) {
        List<Person>personList =  jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id=person.person_id WHERE book.book_id = ?", new Object[]{id},
                new PersonMapper());
        return personList.stream().findAny();
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public  List<Book> getBookPerson(int personId) {
        return jdbcTemplate.query("SELECT book.* FROM book JOIN person ON book.person_id=person.person_id WHERE book.person_id = ?", new Object[]{personId},
                new BookMapper());
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id= ?", bookId);
        System.out.println("Method release bookId= " + bookId);
    }
    public void assign(int personId, int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id= ?", personId,bookId);
        System.out.println("Method assign bookId= " + bookId + " personId= "+ personId);

    }
}
