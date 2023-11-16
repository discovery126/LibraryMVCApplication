package ru.example.spring.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class Person {

    private int personId;
    @Size(min = 5,max = 40,message = "Слишком короткое или слишком длинное ФИО")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+",message = "ФИО должно соответствовать шаблону 'Фамилия Имя Отчество'")
    private String fio;
    @Min(value = 1923, message = "Ошибка в годе рождения")
    @Max(value = 2010, message = "Ошибка в годе рождения")
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
