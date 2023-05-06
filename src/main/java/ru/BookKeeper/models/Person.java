package ru.BookKeeper.models;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "имя не должно быть пустым")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "ваше ФИО должно быть в таком формате: Иванов Иван Иванович")
    @Column(name = "name")
    private String name;

    @Column(name = "year_birth")
    private int year_birth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;



    public Person(int id, String name, int year_birth) {
        this.id = id;
        this.name = name;
        this.year_birth = year_birth;
    }
    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_birth() {
        return year_birth;
    }

    public void setYear_birth(int year_birth) {
        this.year_birth = year_birth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
