package lt.kucinskas.BookRater.service.mapper;

import lt.kucinskas.BookRater.dto.BookDTO;
import lt.kucinskas.BookRater.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book convertToEntity (BookDTO bookDTO) {
        if (bookDTO == null) {
                return null;
        }
        return Book.builder()
                .id(bookDTO.getId())
                .authorId(bookDTO.getAuthorId())
                .title(bookDTO.getTitle())
                .year(bookDTO.getYear())
                .build();
        }

    public BookDTO convertToDto (Book book) {
        if (book == null) {
               return null;
        }
        return BookDTO.builder()
                .id(book.getId())
                .authorId(book.getAuthorId())
                .title(book.getTitle())
                .year(book.getYear())
                .build();
    }
}

