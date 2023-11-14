package ru.example.spring.models;

public class Person {
    private int personId;
    private String FIO;
    private int birthDate;

    public Person() {}
    public Person(String FIO, int bitrthDate) {
        this.FIO = FIO;
        this.birthDate = bitrthDate;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}
