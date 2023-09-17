package bookstore.controllers.api;

import bookstore.dto.BookDto;
import bookstore.dto.BooksDto;
import bookstore.dto.NewBookDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api")
public interface BooksApi {

  @PostMapping("/book/")
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<BookDto> addBook(
      @Parameter(required = true) @RequestBody NewBookDto newBook);


  @GetMapping("/book/{book-name}")
  ResponseEntity <BooksDto> getBook (@Parameter(required = true, description = "Book name")
   @PathVariable("book-name") String parameter);


  @GetMapping("/books")
  ResponseEntity<BooksDto> geAllBooks();
}



