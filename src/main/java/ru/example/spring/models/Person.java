package ru.example.spring.models;

public class Person {
    private int personId;
    private String fio;
    private int birthDate;

    public Person() {}
    public Person(String fio, int bitrthDate) {
        this.fio = fio;
        this.birthDate = bitrthDate;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}
