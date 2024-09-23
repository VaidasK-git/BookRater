package lt.kucinskas.BookRater.repository;

import lt.kucinskas.BookRater.model.BookRating;
import lt.kucinskas.BookRater.model.BookRatingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, Long> {

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "GROUP BY b.id, a.firstName, a.lastName, b.id, b.title, b.year " +
            "ORDER BY b.id")
    List<BookRatingResponse> getBookRatingDetails();

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "WHERE a.firstName = :firstName AND a.lastName = :lastName " +
            "GROUP BY b.id, a.firstName, a.lastName, b.id, b.title, b.year " +
            "ORDER BY b.id")
    List<BookRatingResponse> filterBooksByAuthor(String firstName, String lastName);

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "WHERE b.title = :title " +
            "GROUP BY b.id, a.firstName, a.lastName, b.id, b.title, b.year " +
            "ORDER BY b.id")
    List<BookRatingResponse> filterBooksByTitle(String title);

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "WHERE b.year = :year " +
            "GROUP BY b.id, a.firstName, a.lastName, b.id, b.title, b.year " +
            "ORDER BY b.id")
    List<BookRatingResponse> filterBooksByYear(Integer year);

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "GROUP BY a.firstName, a.lastName, b.id, b.title, b.year " +
            "HAVING ROUND(AVG(br.rating), 2) BETWEEN :minRating AND :maxRating " +
            "ORDER BY b.id")
    List<BookRatingResponse> filterBooksByRating(Double minRating, Double maxRating);

    @Query("SELECT new lt.kucinskas.BookRater.model.BookRatingResponse(b.id, a.firstName, a.lastName, b.title, b.year, ROUND(AVG(br.rating), 2)) " +
            "FROM Book b " +
            "JOIN Author a ON a.id = b.authorId " +
            "LEFT JOIN BookRating br ON br.bookId = b.id " +
            "GROUP BY a.firstName, a.lastName, b.id, b.title, b.year " +
            "HAVING ROUND(AVG(br.rating), 2) IS NULL " +
            "ORDER BY b.id")
    List<BookRatingResponse> filterBooksByRatingNull();
}
