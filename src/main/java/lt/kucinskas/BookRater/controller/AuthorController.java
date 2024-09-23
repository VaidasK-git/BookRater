package lt.kucinskas.BookRater.controller;

import jakarta.validation.Valid;
import lt.kucinskas.BookRater.dto.AuthorDTO;
import lt.kucinskas.BookRater.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(
            @Valid @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorDTO));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long authorId) {
        if (authorService.existingAuthor(authorId)) {
            return ResponseEntity.status(HttpStatus.OK).body(authorService.getAuthorById(authorId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(
            @PathVariable("authorId") Long authorId,
            @Valid @RequestBody AuthorDTO authorDTO) {
        if (authorService.existingAuthor(authorId)) {
            return ResponseEntity.ok(authorService.updateAuthor(authorId, authorDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> deleteAuthor(
            @PathVariable("authorId") Long authorId) {
        if (authorService.existingAuthor(authorId)) {
            authorService.deleteAuthor(authorId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
