package ru.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByFio(String fio);
    @Query("select p from Person p join Book b on p.personId=b.owner.personId where b.bookId=:bookId")
    Person getOwnerBook(@Param("bookId") int bookId);
}
