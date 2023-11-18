package ru.example.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "name_book")
    @Size(min = 1,max = 50,message = "Длина названия книги слишком маленькая или слишком большая")
    @Pattern(regexp = "[A-ZА-ЯЁ][a-zа-яё ]+",message = "Название книги должно быть с большой буквы")
    private String nameBook;

    @Column(name = "author_book")
    @NotEmpty(message = "Автор не может быть пустым")
    @Pattern(regexp = "[A-ZА-ЯЁ][a-zа-яё]+ [A-ZА-ЯЁ][a-zа-яё]+",message = "Имя и фамилия автора должно быть по шаблону 'Имя Фамилия'")
    private String authorBook;

    @Min(value = 0,message = "Дата не может быть отрицательной")
    @Max(value = 2023,message = "Дата не может быть больше текущего года")
    @Column(name = "date_publication")
    private int datePublication;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "person_id")
    private Person owner;

    public Book() {}

    public Book(String nameBook, String authorBook, int datePublication) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.datePublication = datePublication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }

    public int getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(int datePublication) {
        this.datePublication = datePublication;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        if (owner != null)
            owner.addBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return datePublication == book.datePublication && Objects.equals(nameBook, book.nameBook) && Objects.equals(authorBook, book.authorBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBook, authorBook, datePublication);
    }

    @Override
    public String toString() {
        return "Book{" +
                "nameBook='" + nameBook + '\'' +
                ", authorBook='" + authorBook + '\'' +
                ", datePublication=" + datePublication +
                '}';
    }
}
