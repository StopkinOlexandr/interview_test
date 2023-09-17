package bookstore.dto;

import bookstore.models.Book;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String bookName;

  private String author;

  private String printYear;

  public static BookDto from(Book book) {
    return BookDto.builder()
        .id(book.getId())
        .author(book.getAuthor())
        .bookName(book.getBookName())
        .printYear(book.getPrintYear())
        .build();
  }

  public static List<BookDto> from(List<Book> books) {
    return books.stream()
        .map(BookDto::from)
        .collect(Collectors.toList());
  }
}
