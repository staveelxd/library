package ru.kgeu.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kgeu.library.model.Author;
import ru.kgeu.library.repo.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            author.setBirthYear(updatedAuthor.getBirthYear());
            return authorRepository.save(author);
        });
    }

    public boolean deleteAuthor(Long id) {
        return authorRepository.findById(id).map(author -> {
            authorRepository.delete(author);
            return true;
        }).orElse(false);
    }
}
