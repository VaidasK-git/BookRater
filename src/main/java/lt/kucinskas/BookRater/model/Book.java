package lt.kucinskas.BookRater.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "books")
public class Book extends Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @NotNull
    @Size(min =1, max = 255)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Min(1)
    @Max(2024)
    @Column(name = "year", nullable = false)
    private Integer year;

}
