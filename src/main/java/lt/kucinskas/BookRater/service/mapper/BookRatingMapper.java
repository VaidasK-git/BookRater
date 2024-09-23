package lt.kucinskas.BookRater.service.mapper;

import lt.kucinskas.BookRater.dto.BookRatingDTO;
import lt.kucinskas.BookRater.model.BookRating;
import org.springframework.stereotype.Component;

@Component
public class BookRatingMapper {

    public BookRating convertToEntity (BookRatingDTO bookRatingDTO) {
        if (bookRatingDTO == null) {
            return null;
        }
        return BookRating.builder()
                .id(bookRatingDTO.getId())
                .bookId(bookRatingDTO.getBookId())
                .rating(bookRatingDTO.getRating())
                .build();
    }

    public BookRatingDTO convertToDto (BookRating bookRating) {
        if (bookRating == null) {
            return null;
        }
        return BookRatingDTO.builder()
                .id(bookRating.getId())
                .bookId(bookRating.getBookId())
                .rating(bookRating.getRating())
                .build();
    }

}
