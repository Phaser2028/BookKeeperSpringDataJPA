package ru.BookKeeper.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;
import ru.BookKeeper.repositories.BooksRepository;
import ru.BookKeeper.repositories.PeopleRepository;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository, PeopleRepository peopleRepository1) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }


    //получить список всех книг из БД
    public List<Book> findAll() {
        return booksRepository.findAll();
    }





    //разбиение страницы со списком всех книг на страницы по books_per_page на каждой странице
    public List<Book> findAll(int page,int books_per_page) {
        return booksRepository.findAll(PageRequest.of(page,books_per_page)).getContent();
    }





    //выдать книгу
    public void assign(int id,Person person){
        findOne(id).setOwner(person);//присвоить книге владельца
        findOne(id).setDateOfTaking(new Date());
    }

    ////вернуть книгу обратно
    public void bringBack(int id,Person person){
        findOne(id).setOwner(null);
        findOne(id).setDateOfTaking(null);
    }

    //вернуть владельца по id книги
    public Person getOwner(int id){
        return findOne(id).getOwner();
    }

    //вернуть книгу по первым буквам в названии
    public  Optional<Book> findByTitleStartingWith(String title){
        return booksRepository.findByTitleStartingWith(title);
    }

    //найти книгу по в id
    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }



    //сохранить книгу
    public void save(Book book) {
        booksRepository.save(book);
    }

    //обновить книгу
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }


    //удалить книгу
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
