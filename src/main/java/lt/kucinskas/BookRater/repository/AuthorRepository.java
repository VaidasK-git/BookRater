package lt.kucinskas.BookRater.repository;

import lt.kucinskas.BookRater.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

