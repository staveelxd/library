package ru.kgeu.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kgeu.library.dto.AuthorDTO;
import ru.kgeu.library.model.Author;
import ru.kgeu.library.repo.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(author.getId());
                    dto.setName(author.getName());
                    dto.setBirthYear(author.getBirthYear());
                    return dto;
                });
    }

    // Получение автора по ID
    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(author.getId());
                    dto.setName(author.getName());
                    dto.setBirthYear(author.getBirthYear());
                    return dto;
                });
    }

    public AuthorDTO createAuthor(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthYear(dto.getBirthYear());
        Author saved = authorRepository.save(author);

        AuthorDTO result = new AuthorDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setBirthYear(saved.getBirthYear());
        return result;
    }


    public Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO dto) {
        return authorRepository.findById(id).map(author -> {
            author.setName(dto.getName());
            author.setBirthYear(dto.getBirthYear());
            Author saved = authorRepository.save(author);

            AuthorDTO result = new AuthorDTO();
            result.setId(saved.getId());
            result.setName(saved.getName());
            result.setBirthYear(saved.getBirthYear());
            return result;
        });
    }

    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
