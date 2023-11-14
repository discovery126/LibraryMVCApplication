package spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class PersonDao {
    JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
