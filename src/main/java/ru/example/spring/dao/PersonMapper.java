package ru.example.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.example.spring.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPersonId(rs.getInt("person_id"));
        person.setFIO(rs.getString("fio"));
        person.setBirthDate(rs.getInt("birth_date"));
        return person;
    }
}
