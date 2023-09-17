package bookstore.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import bookstore.dto.NewBookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BooksController working: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class BooksControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  void addBookTest() throws Exception {
    String body = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book one")
        .printYear("1999")
        .author("Remark")
        .build());

    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.bookName", is("book one")))
        .andExpect(jsonPath("$.author", is("Remark")))
        .andExpect(jsonPath("$.printYear", is("1999")));
  }

  @Test
  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  void getAllBooksTest() throws Exception {
    String body1 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book one")
        .printYear("1999")
        .author("Remark")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body1))
        .andExpect(status().isCreated());

    String body2 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book two")
        .printYear("2000")
        .author("Geothe")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body2))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.books.size()", is(2)));
  }

  @Test
  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  void getBookByPartOfNameTwoBooksTest() throws Exception {
    String body1 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book one")
        .printYear("1999")
        .author("Remark")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body1))
        .andExpect(status().isCreated());

    String body2 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book two")
        .printYear("2000")
        .author("Geothe")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body2))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/book/book"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.books.size()", is(2)));
  }

  @Test
  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  void getByFullNameBookTest() throws Exception {
    String body1 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book one")
        .printYear("1999")
        .author("Remark")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body1))
        .andExpect(status().isCreated());

    String body2 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book two")
        .printYear("2000")
        .author("Geothe")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body2))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/book/book two"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.books.size()", is(1)))
        .andExpect(jsonPath("$.books[0].author", is("Geothe")));
  }

  @Test
  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  void getNotExistingNameBookTest() throws Exception {
    String body1 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book one")
        .printYear("1999")
        .author("Remark")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body1))
        .andExpect(status().isCreated());

    String body2 = objectMapper.writeValueAsString(NewBookDto.builder()
        .bookName("book two")
        .printYear("2000")
        .author("Geothe")
        .build());
    mockMvc.perform(post("/api/book/")
            .header("Content-Type", "application/json")
            .content(body2))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/book/book three"))
//        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.books.size()", is(0)));
  }

}



