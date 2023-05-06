package ru.BookKeeper.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "название не должно быть пустым")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "имя автора не должно быть пустым")
    @Column(name = "author")
    private String author;

    @Column(name = "publication_year")
    private int publication_year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;


    @Column(name = "date_of_taking")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTaking;

    @Transient
    private boolean overdue;

    public Book(int id, String title, String author, int publication_year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
    }
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    public Date getDateOfTaking() {
        return dateOfTaking;
    }

    public void setDateOfTaking(Date dateOfTaking) {
        this.dateOfTaking = dateOfTaking;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
