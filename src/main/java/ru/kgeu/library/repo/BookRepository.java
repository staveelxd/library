package ru.kgeu.library.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kgeu.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);

    Page<Book> findByAuthorId(Long authorId, Pageable pageable);
}
