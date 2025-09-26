package ru.kgeu.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kgeu.library.dto.BookDTO;
import ru.kgeu.library.model.Author;
import ru.kgeu.library.model.Book;
import ru.kgeu.library.repo.AuthorRepository;
import ru.kgeu.library.repo.BookRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setDescription(book.getDescription());
                    dto.setYear(book.getYear());
                    dto.setGenre(book.getGenre());
                    dto.setAuthorName(book.getAuthor().getName());
                    return dto;
                });

    }

    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setDescription(book.getDescription());
                    dto.setYear(book.getYear());
                    dto.setGenre(book.getGenre());
                    dto.setAuthorId(book.getAuthor().getId());
                    dto.setAuthorName(book.getAuthor().getName());
                    return dto;
                });
    }

    public BookDTO createBook(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setYear(dto.getYear());
        book.setGenre(dto.getGenre());

        // Найти автора по ID
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Автор не найден"));
        book.setAuthor(author);

        Book saved = bookRepository.save(book);

        BookDTO result = new BookDTO();
        result.setId(saved.getId());
        result.setTitle(saved.getTitle());
        result.setDescription(saved.getDescription());
        result.setYear(saved.getYear());
        result.setGenre(saved.getGenre());
        result.setAuthorId(saved.getAuthor().getId());
        result.setAuthorName(saved.getAuthor().getName());
        return result;
    }

    public Optional<BookDTO> updateBook(Long id, BookDTO dto) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(dto.getTitle());
            book.setDescription(dto.getDescription());
            book.setYear(dto.getYear());
            book.setGenre(dto.getGenre());

            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Автор не найден"));
            book.setAuthor(author);

            Book saved = bookRepository.save(book);

            BookDTO result = new BookDTO();
            result.setId(saved.getId());
            result.setTitle(saved.getTitle());
            result.setDescription(saved.getDescription());
            result.setYear(saved.getYear());
            result.setGenre(saved.getGenre());
            result.setAuthorId(saved.getAuthor().getId());
            result.setAuthorName(saved.getAuthor().getName());
            return result;
        });
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Page<BookDTO> searchByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setDescription(book.getDescription());
                    dto.setYear(book.getYear());
                    dto.setGenre(book.getGenre());
                    dto.setAuthorId(book.getAuthor().getId());
                    dto.setAuthorName(book.getAuthor().getName());
                    return dto;
                });
    }

    public Page<BookDTO> searchByGenre(String genre, Pageable pageable) {
        return bookRepository.findByGenreIgnoreCase(genre, pageable)
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setDescription(book.getDescription());
                    dto.setYear(book.getYear());
                    dto.setGenre(book.getGenre());
                    dto.setAuthorId(book.getAuthor().getId());
                    dto.setAuthorName(book.getAuthor().getName());
                    return dto;
                });
    }

    public Page<BookDTO> searchByAuthor(Long authorId, Pageable pageable) {
        return bookRepository.findByAuthorId(authorId, pageable)
                .map(book -> {
                    BookDTO dto = new BookDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setDescription(book.getDescription());
                    dto.setYear(book.getYear());
                    dto.setGenre(book.getGenre());
                    dto.setAuthorId(book.getAuthor().getId());
                    dto.setAuthorName(book.getAuthor().getName());
                    return dto;
                });
    }
}
