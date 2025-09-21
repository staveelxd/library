package ru.kgeu.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kgeu.library.dto.BookDTO;
import ru.kgeu.library.mapper.BookMapper;
import ru.kgeu.library.model.Book;
import ru.kgeu.library.service.BookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable)
                .map(bookMapper::toDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(bookMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return bookMapper.toDTO(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook)
                .map(bookMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // ===== Поиск =====

    @GetMapping("/search/title")
    public Page<BookDTO> searchByTitle(@RequestParam String title, Pageable pageable) {
        return bookService.searchByTitle(title, pageable)
                .map(bookMapper::toDTO);
    }

    @GetMapping("/search/genre")
    public Page<BookDTO> searchByGenre(@RequestParam String genre, Pageable pageable) {
        return bookService.searchByGenre(genre, pageable)
                .map(bookMapper::toDTO);
    }

    @GetMapping("/search/author")
    public Page<BookDTO> searchByAuthor(@RequestParam Long authorId, Pageable pageable) {
        return bookService.searchByAuthor(authorId, pageable)
                .map(bookMapper::toDTO);
    }
}
