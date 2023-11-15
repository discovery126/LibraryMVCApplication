package ru.example.spring.models;

public class Book {
    private int book_id;
    private String nameBook;
    private String authorBook;
    private int datePublication;
    private int person_id;

    public Book() {}

    public Book(String nameBook, String authorBook, int datePublication,int person_id) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.datePublication = datePublication;
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
