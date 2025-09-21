package ru.kgeu.library.mapper;


import org.springframework.stereotype.Component;
import ru.kgeu.library.dto.AuthorDTO;
import ru.kgeu.library.dto.BookDTO;
import ru.kgeu.library.model.Author;

import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .birthYear(author.getBirthYear())
                .books(author.getBooks() != null ? author.getBooks().stream()
                        .map(book -> BookDTO.builder()
                                .id(book.getId())
                                .title(book.getTitle())
                                .description(book.getDescription())
                                .year(book.getYear())
                                .genre(book.getGenre())
                                .authorId(author.getId())
                                .authorName(author.getName())
                                .build())
                        .collect(Collectors.toList()) : null)
                .build();
    }
}
