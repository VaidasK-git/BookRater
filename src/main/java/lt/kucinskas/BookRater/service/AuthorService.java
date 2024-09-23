package lt.kucinskas.BookRater.service;

import lt.kucinskas.BookRater.dto.AuthorDTO;
import lt.kucinskas.BookRater.model.Author;
import lt.kucinskas.BookRater.repository.AuthorRepository;
import lt.kucinskas.BookRater.service.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.convertToEntity(authorDTO);
        Author savedCustomer = authorRepository.save(author);
        return authorMapper.convertToDto(savedCustomer);
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO  getAuthorById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            return authorMapper.convertToDto(existingAuthor);
        }
        return null;
    }

    public boolean existingAuthor(Long id) {
        return authorRepository.existsById(id);
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.setFirstName(authorDTO.getFirstName());
            existingAuthor.setLastName(authorDTO.getLastName());
            Author updatedAuthor = authorRepository.save(existingAuthor);
            return authorMapper.convertToDto(updatedAuthor);
        }
        return null;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
