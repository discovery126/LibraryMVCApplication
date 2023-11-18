package ru.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findByNameBook(String nameBook);
    @Query("SELECT b FROM Book b join Person p on p.personId=b.owner.personId where p.personId=:personId")
    List<Book> getBookPerson(@Param("personId") int personId);
}
