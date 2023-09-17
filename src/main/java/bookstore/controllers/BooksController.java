package bookstore.controllers;

import bookstore.controllers.api.BooksApi;
import bookstore.dto.BookDto;
import bookstore.dto.BooksDto;
import bookstore.dto.NewBookDto;
import bookstore.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class BooksController implements BooksApi {

    private final BooksService booksService;

    @Override
    public ResponseEntity<BookDto> addBook(NewBookDto newBook) {

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(booksService.addBook(newBook));
    }

    @Override
    public ResponseEntity<BooksDto> getBook(String bookName) {
        return ResponseEntity
            .ok(booksService.getBook(bookName));
    }

    @Override
    public ResponseEntity<BooksDto> geAllBooks() {
        return ResponseEntity
            .ok(booksService.getAllBooks());
    }
}
