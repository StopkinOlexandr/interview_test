package bookstore.services;

import bookstore.dto.BookDto;
import bookstore.dto.BooksDto;
import bookstore.dto.NewBookDto;

public interface BooksService {

  BookDto addBook(NewBookDto newBook);

  BooksDto getAllBooks();

  BooksDto getBook(String bookName);
}
