package ru.example.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setBookId(rs.getInt("book_id"));
        book.setNameBook(rs.getString("name_book"));
        book.setAuthorBook(rs.getString("author_book"));
        book.setDatePublication(rs.getInt("date_publication"));
        book.setPersonId(rs.getInt("person_id"));
        return book;
    }
}
