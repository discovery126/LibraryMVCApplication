package ru.example.spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ru.example.spring.models.Person;
import ru.example.spring.util.PersonValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonDao {
    private JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPerson() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY person_id", new PersonMapper());
    }

    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO person(fio, birth_date) VALUES(?, ?)",
                person.getFio(),
                person.getBirthDate()
        );
    }


    public Person getPerson(int id) {
        List<Person> personList = jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[]{id},
                new PersonMapper());
        return personList.stream().findAny().orElse(null);
    }


    public Optional<Person> getPerson(String fio) {
        List<Person> personList = jdbcTemplate.query("SELECT * FROM person WHERE fio=?", new Object[]{fio},
                new PersonMapper());
        return  personList.stream().findAny();
    }


    public void update(Person updatedPerson, int id) {
        jdbcTemplate.update("UPDATE person SET fio=?, birth_date=? WHERE person_id= ?",
                updatedPerson.getFio(),
                updatedPerson.getBirthDate(),
                id);
    }


    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
}
