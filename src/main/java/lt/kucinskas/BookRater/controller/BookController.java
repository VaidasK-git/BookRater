package lt.kucinskas.BookRater.controller;

import jakarta.validation.Valid;
import lt.kucinskas.BookRater.dto.BookDTO;
import lt.kucinskas.BookRater.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(
            @Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        if (bookService.existingBook(bookId)) {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable("bookId") Long bookId,
            @Valid @RequestBody BookDTO bookDTO) {
        if (bookService.existingBook(bookId)) {
            return ResponseEntity.ok(bookService.updateBook(bookId, bookDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookDTO> deleteBook(
            @PathVariable("authorId") Long bookId) {
        if (bookService.existingBook(bookId)) {
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
