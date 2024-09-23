package lt.kucinskas.BookRater.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRatingDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long bookId;

    @Min(1)
    @Max(5)
    private Integer rating;

}
