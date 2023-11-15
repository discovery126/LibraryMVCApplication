package ru.example.spring.models;

public class Book {
    private int bookId;
    private String nameBook;
    private String authorBook;
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
