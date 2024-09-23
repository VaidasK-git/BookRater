package lt.kucinskas.BookRater.controller;

import jakarta.validation.Valid;
import lt.kucinskas.BookRater.dto.BookRatingDTO;
import lt.kucinskas.BookRater.model.BookRatingResponse;
import lt.kucinskas.BookRater.service.BookRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookRatings")
public class BookRatingController {

    private final BookRatingService bookRatingService;

    public BookRatingController(BookRatingService bookRatingService) {
        this.bookRatingService = bookRatingService;
    }

    @PostMapping
    public ResponseEntity<BookRatingDTO> createBookRating(
            @Valid @RequestBody BookRatingDTO bookRatingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRatingService.createBookRating(bookRatingDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookRatingDTO>> getAllBooksRating() {
        return ResponseEntity.ok(bookRatingService.getAllBooksRating());
    }

    @GetMapping("/{bookRatingId}")
    public ResponseEntity<BookRatingDTO> getBookRatingById(@PathVariable Long bookRatingId) {
        if (bookRatingService.existingBookRating(bookRatingId)) {
            return ResponseEntity.status(HttpStatus.OK).body(bookRatingService.getBookRatingById(bookRatingId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookRatingId}")
    public ResponseEntity<BookRatingDTO> updateBookRating(
            @PathVariable("bookRatingId") Long bookRatingId,
            @Valid @RequestBody BookRatingDTO bookRatingDTO) {
        if (bookRatingService.existingBookRating(bookRatingId)) {
            return ResponseEntity.ok(bookRatingService.updateBookRating(bookRatingId, bookRatingDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookRatingId}")
    public ResponseEntity<BookRatingDTO> deleteBookRating(
            @PathVariable("bookRatingId") Long bookRatingId) {
        if (bookRatingService.existingBookRating(bookRatingId)) {
            bookRatingService.deleteBookRating(bookRatingId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ratings")
    public List<BookRatingResponse> getBookRatingDetails() {
        return bookRatingService.getBookRatingDetails();
    }

    @GetMapping("/filter/author")
    public List<BookRatingResponse> filterBooksByAuthor(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return bookRatingService.filterBooksByAuthor(firstName, lastName);
    }

    @GetMapping("/filter/title")
    public List<BookRatingResponse> filterBooksByTitle(
            @RequestParam String title) {
        return bookRatingService.filterBooksByTitle(title);
    }

    @GetMapping("/filter/year")
    public List<BookRatingResponse> filterBooksByYear(
            @RequestParam Integer year) {
        return bookRatingService.filterBooksByYear(year);
    }

    @GetMapping("/filter/rating")
    public List<BookRatingResponse> filterBooksByRating(
            @RequestParam Integer rating) {
        return bookRatingService.filterBooksByRating(rating);
    }

}
