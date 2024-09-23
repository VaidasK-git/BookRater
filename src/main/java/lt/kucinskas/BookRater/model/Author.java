package lt.kucinskas.BookRater.model;

import jakarta.persistence.*;
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
@Table(name = "authors")
public class Author extends Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min =1, max = 35)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min =1, max = 35)
    @Column(name = "last_name", nullable = false)
    private String lastName;

}


