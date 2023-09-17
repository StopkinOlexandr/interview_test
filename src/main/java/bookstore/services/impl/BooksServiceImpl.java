package bookstore.services.impl;

import static bookstore.dto.BookDto.from;
import bookstore.dto.BookDto;
import bookstore.dto.BooksDto;
import bookstore.dto.NewBookDto;
import bookstore.models.Book;
import bookstore.repositories.BookRepository;
import bookstore.services.BooksService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

  private final BookRepository bookRepository;

  @Override
  public BookDto addBook(NewBookDto newBook) {
    Book book = Book.builder().author(newBook.getAuthor()).printYear(newBook.getPrintYear())
        .bookName(newBook.getBookName()).build();
    bookRepository.save(book);
    return from(book);
  }

  @Override
  public BooksDto getAllBooks() {
    List<Book> booksList = bookRepository.findAll();
    return BooksDto.builder().books(from(booksList)).build();
  }

  @Override
  public BooksDto getBook(String bookToFind) {
    List<Book> bookList = bookRepository.findAll();
    return BooksDto.builder()
        .books(from(bookList.stream().filter(p -> p.getBookName().contains(bookToFind)).toList()))
        .build();
  }
}
