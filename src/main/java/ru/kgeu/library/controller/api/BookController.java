package ru.kgeu.library.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
        return bookService.getAllBooks(pageable);
    }

    // Получение книги по ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание новой книги
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO created = bookService.createBook(bookDTO);
        return ResponseEntity.ok(created);
    }

    // Обновление книги
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление книги
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public Page<BookDTO> searchByTitle(@RequestParam String title, Pageable pageable) {
        return bookService.searchByTitle(title, pageable);
    }

    @GetMapping("/search/genre")
    public Page<BookDTO> searchByGenre(@RequestParam String genre, Pageable pageable) {
        return bookService.searchByGenre(genre, pageable);
    }

    @GetMapping("/search/author")
    public Page<BookDTO> searchByAuthor(@RequestParam Long authorId, Pageable pageable) {
        return bookService.searchByAuthor(authorId, pageable);
    }
}
