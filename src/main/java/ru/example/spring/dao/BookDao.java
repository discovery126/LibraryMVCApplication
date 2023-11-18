package ru.example.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.example.spring.models.Book;
import ru.example.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private final SessionFactory sessionFactory;
    @Autowired
    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBook() {
        Session session = sessionFactory.getCurrentSession();
        List<Book>bookList =  session.createQuery("select b from Book b order by bookId",Book.class).getResultList();
        return bookList;
    }

    @Transactional
    public void create(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public Book getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }

    @Transactional
    public Optional<Book> getBook(String currentNameBook) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b where nameBook = :currentNameBook",Book.class)
                .setParameter("currentNameBook",currentNameBook)
                .stream().findAny();
    }

    @Transactional
    public void updateBook(Book updatedBook, int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        book.setNameBook(updatedBook.getNameBook());
        book.setAuthorBook(updatedBook.getAuthorBook());
        book.setDatePublication(updatedBook.getDatePublication());
    }

    @Transactional
    public Optional<Person> getOwnerBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p join Book b on p.personId=b.owner.personId where b.bookId=:bookId",
                        Person.class)
                .setParameter("bookId",bookId)
                .getResultList().stream().findAny();
    }

    @Transactional
    public  List<Book> getBookPerson(int personId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b FROM Book b join Person p on p.personId=b.owner.personId where p.personId=:personId",Book.class)
                .setParameter("personId",personId)
                .getResultList();
    }

    @Transactional
    public void release(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,bookId);
        book.setOwner(null);
    }

    @Transactional
    public void assign(int personId, int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,bookId);
        Person person = session.get(Person.class,personId);
        book.setOwner(person);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        session.remove(book);
    }
}
