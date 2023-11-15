package ru.example.spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.util.List;

@Component
public class BookDao {
    JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllBook() {
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
    public void updateBook(Person updatedPerson, int id) {
        jdbcTemplate.update("UPDATE book SET name_book=?,author_book=?, date_publication=? WHERE person_id= ?",
                updatedPerson.getFio(),
                updatedPerson.getBirthDate(),
                id);
    }
//    public void updateBook(Person updatedPerson, int id) {
//        jdbcTemplate.update("UPDATE book SET name_book=?,author_book=?, date_publication=? WHERE person_id= ?",
//                updatedPerson.getFio(),
//                updatedPerson.getBirthDate(),
//                id);
//    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public  List<Book> getBookPerson(int personId) {
        return jdbcTemplate.query("SELECT book.* FROM book JOIN person ON book.person_id=person.person_id WHERE book.person_id = ?", new Object[]{personId},
                new BookMapper());
    }
}
