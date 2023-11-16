package ru.example.spring.util;

import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.spring.dao.PersonDao;
import ru.example.spring.models.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;
    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDao.getPerson(person.getFio()).isPresent()) {
            errors.rejectValue("fio","","Такой человек уже существует");
        }

    }
}
