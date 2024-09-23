package lt.kucinskas.BookRater.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRatingResponse {
    private Long bookId;
    private String firstName;
    private String lastName;
    private String title;
    private Integer year;
    private Double rating;

}
