package ru.BookKeeper.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByTitleStartingWith(String title);


//    Page<Book> findAll(Pageable pageable);///////////









    Book findByTitleLike(String title);
    List<Book> findByOwner(Person person);


}
