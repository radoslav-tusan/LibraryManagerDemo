package com.rtusan.librarymanagerdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtusan.librarymanagerdemo.api.model.BookDto;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = LibraryManagerDemoApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest extends IntegrationTestBase {

  private final String ENDPOINT = "/book";

  @Test
  public void thatBookCanBeRetrievedById() throws JSONException {
    final int id = 1;

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + id),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"id\":1,\"name\":\"Gardening\",\"description\":\"Guide to make your garden cozier\",\"author\":\"Igor Hruška\",\"publisher\":\"IKAR\"}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatAllBooksCanBeRetrieved() throws JSONException {
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"content\":[{\"id\":1,\"name\":\"Gardening\",\"description\":\"Guide to make your garden cozier\",\"author\":\"Igor Hruška\",\"publisher\":\"IKAR\"},"
            + "{\"id\":2,\"name\":\"GameDev\",\"description\":\"Game development tutorials\",\"author\":\"Rado Tušan\",\"publisher\":\"IKAR\"},"
            + "{\"id\":3,\"name\":\"Rýchle šípy\",\"author\":\"Jaroslav Foglár\",\"publisher\":\"ČSSR\"},{\"id\":4,\"name\":\"Šachy\",\"author\":\"Peťo šachmajster\"},"
            + "{\"id\":5,\"name\":\"Vývoj ľudstva\",\"description\":\"Antropologická kniha\",\"author\":\"Doktor Sova\",\"publisher\":\"IKAR\"}],"
            + "\"pageable\":{\"pageNumber\":0,\"pageSize\":20,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},"
            + "\"totalPages\":1,\"totalElements\":5,\"last\":true,\"size\":20,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":5,\"first\":true,\"empty\":false}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatBookCanBeCreated() throws JsonProcessingException {
    final String name = "New Name";
    final String desc = "New Desc";
    final String author = "Fancy Author";
    final String publisher = "Well Known Publisher";

    BookDto newBook = new BookDto(name, desc, author, publisher);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newBook, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseDesc = objectMapper.readTree(response.getBody()).path("description").asText();
    String responseAuthor = objectMapper.readTree(response.getBody()).path("author").asText();
    String responsePublisher = objectMapper.readTree(response.getBody()).path("publisher").asText();

    assertEquals(name, responseName);
    assertEquals(desc, responseDesc);
    assertEquals(author, responseAuthor);
    assertEquals(publisher, responsePublisher);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatBookCanBeCreatedAndUpdated() throws JsonProcessingException {
    final String name = "New Name";
    final String desc = "New Desc";
    final String author = "Fancy Author";
    final String publisher = "Well Known Publisher";

    BookDto newBook = new BookDto(name, desc, author, publisher);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newBook, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseDesc = objectMapper.readTree(response.getBody()).path("description").asText();
    String responseAuthor = objectMapper.readTree(response.getBody()).path("author").asText();
    String responsePublisher = objectMapper.readTree(response.getBody()).path("publisher").asText();

    assertEquals(name, responseName);
    assertEquals(desc, responseDesc);
    assertEquals(author, responseAuthor);
    assertEquals(publisher, responsePublisher);

    final String updatedName = "Updated Name";
    final String updatedDesc = "Updated Desc";
    final String updatedAuthor = "Updated Author";
    final String updatedPublisher = "Updated Publisher";

    BookDto updatedBook = new BookDto(updatedName, updatedDesc, updatedAuthor, updatedPublisher);

    ResponseEntity<String> updatResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.PUT,
            new HttpEntity<>(updatedBook, headers),
            String.class);

    String updatedResponseName =
        objectMapper.readTree(updatResponse.getBody()).path("name").asText();
    String updatedResponseDesc =
        objectMapper.readTree(updatResponse.getBody()).path("description").asText();
    String updatedResponseAuthor =
        objectMapper.readTree(updatResponse.getBody()).path("author").asText();
    String updatedResponsePublisher =
        objectMapper.readTree(updatResponse.getBody()).path("publisher").asText();

    assertEquals(updatedName, updatedResponseName);
    assertEquals(updatedDesc, updatedResponseDesc);
    assertEquals(updatedAuthor, updatedResponseAuthor);
    assertEquals(updatedPublisher, updatedResponsePublisher);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatBookCanBeCreatedAndDeleted() throws JsonProcessingException {
    final String name = "New Name";
    final String desc = "New Desc";
    final String author = "Fancy Author";
    final String publisher = "Well Known Publisher";

    BookDto newBook = new BookDto(name, desc, author, publisher);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newBook, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseDesc = objectMapper.readTree(response.getBody()).path("description").asText();
    String responseAuthor = objectMapper.readTree(response.getBody()).path("author").asText();
    String responsePublisher = objectMapper.readTree(response.getBody()).path("publisher").asText();

    assertEquals(name, responseName);
    assertEquals(desc, responseDesc);
    assertEquals(author, responseAuthor);
    assertEquals(publisher, responsePublisher);

    ResponseEntity<String> deleteResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.DELETE,
            new HttpEntity<>(null, headers),
            String.class);

    assertEquals(deleteResponse.getStatusCode(), HttpStatusCode.valueOf(204));
  }
}
