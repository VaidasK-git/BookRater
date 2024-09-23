package lt.kucinskas.BookRater.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.kucinskas.BookRater.model.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO extends Date {

    @NotNull
    private Long id;

    @NotNull
    private Long authorId;

    @NotNull
    @Size(min =1, max = 255)
    private String title;

    @NotNull
    @Min(1)
    @Max(2024)
    private Integer year;

    public @NotNull @Min(1) @Max(2024) Integer getYear() {
        return year;
    }

    public void setYear(@NotNull @Min(1) @Max(2024) Integer year) {
        this.year = year;
    }
}
