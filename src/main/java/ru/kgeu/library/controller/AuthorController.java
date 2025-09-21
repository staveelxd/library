package ru.kgeu.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kgeu.library.dto.AuthorDTO;
import ru.kgeu.library.mapper.AuthorMapper;
import ru.kgeu.library.model.Author;
import ru.kgeu.library.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors().stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id)
                .map(authorMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.createAuthor(author);
        return authorMapper.toDTO(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        return authorService.updateAuthor(id, updatedAuthor)
                .map(authorMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
