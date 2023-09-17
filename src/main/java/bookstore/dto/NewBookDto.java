package bookstore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBookDto {

  private String bookName;

  private String author;

  private String printYear;
}
