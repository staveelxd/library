package ru.kgeu.library.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kgeu.library.dto.AuthorDTO;
import ru.kgeu.library.dto.BookDTO;
import ru.kgeu.library.mapper.AuthorMapper;
import ru.kgeu.library.mapper.BookMapper;
import ru.kgeu.library.service.AuthorService;
import ru.kgeu.library.service.BookService;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/books")
    public String books(Model model, Pageable pageable) {
        var booksPage = bookService.getAllBooks(pageable);
        model.addAttribute("booksPage", booksPage);
        return "books";
    }

    @GetMapping("/authors")
    public String authors(Model model, Pageable pageable) {
        var authorsPage = authorService.getAllAuthors(pageable);
        model.addAttribute("authorsPage", authorsPage);
        return "authors";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("author", new AuthorDTO());
        model.addAttribute("book", new BookDTO());
        return "index";
    }

}
