package ru.BookKeeper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.BookKeeper.models.Book;
import ru.BookKeeper.models.Person;
import ru.BookKeeper.services.BooksService;
import ru.BookKeeper.services.PeopleService;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {


    private final BooksService booksService;

    private final PeopleService peopleService;




    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }




    ///Я НАКОНЕЦ-ТО СДЕЛАЛ ПАГИНАЦИЮ)))))))))))))))))))))))))))))))
    @GetMapping()
    public String index(
            Model model,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer books_per_page) {

        if(page != null && books_per_page !=null)
            model.addAttribute("books",booksService.findAll(page,books_per_page));
        else
            model.addAttribute("books",booksService.findAll());

        return "/books/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String title, Model model) {

        if (title != null) {
            Optional<Book> book = booksService.findByTitleStartingWith(title);
            model.addAttribute("found_book", book);
            return "/books/search";
        }
        return "/books/search";
    }




    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {

        model.addAttribute("book", booksService.findOne(id));

        Person bookOwner = booksService.getOwner(id);

        if (bookOwner != null) {
            model.addAttribute("owner", bookOwner);
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }


    @PostMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {

        booksService.assign(id, person);//присваиваем книгу с айди = id пользователю = person

        return "redirect:/books/" + id;
    }


    @PostMapping("/{id}/back")
    public String getBack(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.bringBack(id, person);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {


        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }


}