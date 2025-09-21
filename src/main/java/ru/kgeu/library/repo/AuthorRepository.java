package ru.kgeu.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kgeu.library.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
