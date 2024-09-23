package lt.kucinskas.BookRater.service.mapper;

import lt.kucinskas.BookRater.dto.AuthorDTO;
import lt.kucinskas.BookRater.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author convertToEntity (AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }
        return Author.builder()
                .id(authorDTO.getId())
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .build();
    }

    public AuthorDTO convertToDto (Author author) {
        if (author == null) {
            return null;
        }
        return AuthorDTO.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
}
