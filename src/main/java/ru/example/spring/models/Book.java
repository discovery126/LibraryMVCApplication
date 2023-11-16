package ru.example.spring.models;

import jakarta.validation.constraints.*;

public class Book {
    private int bookId;
    @Size(min = 1,max = 50,message = "Длина названия книги слишком маленькая или слишком большая")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+",message = "Название книги должно быть с большой буквы")
    private String nameBook;
    @NotEmpty(message = "Автор не может быть пустым")
    @Pattern(regexp = "[A-ZА-ЯЁ][a-zа-яё]+ [A-ZА-ЯЁ][a-zа-яё]+",message = "Имя и фамилия автора должно быть по шаблону 'Имя Фамилия'")
    private String authorBook;
    @Min(value = 0,message = "Дата не может быть отрицательной")
    @Max(value = 2023,message = "Дата не может быть больше текущего года")
    private int datePublication;
    private int personId;

    public Book() {}

    public Book(String nameBook, String authorBook, int datePublication, int personId) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.datePublication = datePublication;
        this.personId = personId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

}
