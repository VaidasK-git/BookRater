package lt.kucinskas.BookRater.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.kucinskas.BookRater.model.Date;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO extends Date {

    @NotNull
    private Long id;

    @NotNull
    @Size(min =1, max = 35)
    private String firstName;

    @NotNull
    @Size(min =1, max = 35)
    private String lastName;

}




