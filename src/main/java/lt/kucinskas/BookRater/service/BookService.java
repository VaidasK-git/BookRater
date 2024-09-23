package lt.kucinskas.BookRater.service;

import lt.kucinskas.BookRater.dto.BookDTO;
import lt.kucinskas.BookRater.model.Book;
import lt.kucinskas.BookRater.repository.BookRepository;
import lt.kucinskas.BookRater.service.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.convertToDto(savedBook);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public BookDTO  getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            return bookMapper.convertToDto(existingBook);
        }
        return null;
    }

    public boolean existingBook(Long id) {
        return bookRepository.existsById(id);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setAuthorId(bookDTO.getAuthorId());
            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setYear(bookDTO.getYear());
            Book updatedBook = bookRepository.save(existingBook);
            return bookMapper.convertToDto(updatedBook);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
