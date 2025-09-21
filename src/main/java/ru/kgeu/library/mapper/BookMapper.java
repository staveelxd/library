package ru.kgeu.library.mapper;

import org.springframework.stereotype.Component;
import ru.kgeu.library.dto.BookDTO;
import ru.kgeu.library.model.Book;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .year(book.getYear())
                .genre(book.getGenre())
                .authorId(book.getAuthor() != null ? book.getAuthor().getId() : null)
                .authorName(book.getAuthor() != null ? book.getAuthor().getName() : null)
                .build();
    }
}