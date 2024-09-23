package lt.kucinskas.BookRater.service;

import lt.kucinskas.BookRater.dto.BookRatingDTO;
import lt.kucinskas.BookRater.model.BookRating;
import lt.kucinskas.BookRater.model.BookRatingResponse;
import lt.kucinskas.BookRater.repository.BookRatingRepository;
import lt.kucinskas.BookRater.service.mapper.BookRatingMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookRatingService {

    private final BookRatingRepository bookRatingRepository;
    private final BookRatingMapper bookRatingMapper;

    public BookRatingService(BookRatingRepository bookRatingRepository, BookRatingMapper bookRatingMapper) {
        this.bookRatingRepository = bookRatingRepository;
        this.bookRatingMapper = bookRatingMapper;
    }

    public BookRatingDTO createBookRating(BookRatingDTO bookRatingDTO) {
        BookRating bookRating = bookRatingMapper.convertToEntity(bookRatingDTO);
        BookRating savedBookRating = bookRatingRepository.save(bookRating);
        return bookRatingMapper.convertToDto(savedBookRating);
    }

    public List<BookRatingDTO> getAllBooksRating() {
        List<BookRating> bookRatings = bookRatingRepository.findAll();
        return bookRatings.stream()
                .map(bookRatingMapper::convertToDto)
                .collect(Collectors.toList());
    }



    public BookRatingDTO  getBookRatingById(Long id) {
        Optional<BookRating> optionalBookRating = bookRatingRepository.findById(id);
        if(optionalBookRating.isPresent()) {
            BookRating existingBookRating = optionalBookRating.get();
            return bookRatingMapper.convertToDto(existingBookRating);
        }
        return null;
    }

    public boolean existingBookRating(Long id) {
        return bookRatingRepository.existsById(id);
    }

    public BookRatingDTO updateBookRating(Long id, BookRatingDTO bookRatingDTO) {
        Optional<BookRating> optionalBookRating = bookRatingRepository.findById(id);
        if (optionalBookRating.isPresent()) {
            BookRating existingBookRating = optionalBookRating.get();
            existingBookRating.setBookId(bookRatingDTO.getBookId());
            existingBookRating.setRating(bookRatingDTO.getRating());
            BookRating updatedBookRating = bookRatingRepository.save(existingBookRating);
            return bookRatingMapper.convertToDto(updatedBookRating);
        }
        return null;
    }

    public void deleteBookRating(Long id) {
        bookRatingRepository.deleteById(id);
    }

    public List<BookRatingResponse> getBookRatingDetails() {
        return bookRatingRepository.getBookRatingDetails();
    }

    public List<BookRatingResponse> filterBooksByAuthor(String firstName, String lastName) {
        return bookRatingRepository.filterBooksByAuthor(firstName, lastName);
    }

    public List<BookRatingResponse> filterBooksByTitle(String title) {
        return bookRatingRepository.filterBooksByTitle(title);
    }

    public List<BookRatingResponse> filterBooksByYear(Integer year) {
        return bookRatingRepository.filterBooksByYear(year);
    }

    public List<BookRatingResponse> filterBooksByRating(Integer rating) {
        if (rating == 0) {
            // Handle the case where rating is 0 to include null ratings
            return bookRatingRepository.filterBooksByRatingNull();
        } else {
            double minRating = rating;
            double maxRating = rating + 0.99;
            return bookRatingRepository.filterBooksByRating(minRating, maxRating);
        }
    }

}
