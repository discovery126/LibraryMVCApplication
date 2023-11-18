package ru.example.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.example.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<Person> getAllPerson() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p",Person.class).getResultList();
    }

    @Transactional
    public void create(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }


    @Transactional
    public Person getPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }


    @Transactional(readOnly = true)
    public Optional<Person> getPerson(String fio) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p where fio=:fio", Person.class)
                .setParameter("fio", fio).stream().findAny();
    }


    @Transactional
    public void update(Person updatedPerson, int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        person.setFio(updatedPerson.getFio());
        person.setBirthDate(updatedPerson.getBirthDate());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        session.remove(person);
    }
}
