package ru.BookKeeper.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;
import ru.BookKeeper.repositories.BooksRepository;
import ru.BookKeeper.repositories.PeopleRepository;


import java.util.*;

@Service
@Transactional
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional(readOnly = false)
    public void save(Person person) {
        peopleRepository.save(person);
    }


    public void update(int id, Person updatedPerson) {

        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);

    }


    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
///////////////////////////////////////////////////////////////////


    public List<Book> findByOwner(Person person){
        return booksRepository.findByOwner(person);
    }

    //проверка на просрочку
    public List<Book> getBooksByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());

            if(person.get().getBooks().size()>0){
                person.get().getBooks().forEach(book -> {
                long days = Math.abs(book.getDateOfTaking().getTime() - new Date().getTime());

                if (days > 864000000) {
                    book.setOverdue(true);
                }
            });}



            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }

}
