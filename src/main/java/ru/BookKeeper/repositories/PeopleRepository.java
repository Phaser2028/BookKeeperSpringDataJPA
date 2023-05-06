package ru.BookKeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findByName(String name);


    Optional<Person> findById(int id);


}
