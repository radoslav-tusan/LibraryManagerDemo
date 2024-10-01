package com.rtusan.librarymanagerdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtusan.librarymanagerdemo.api.model.BookDto;
import com.rtusan.librarymanagerdemo.api.model.CustomerDto;
import com.rtusan.librarymanagerdemo.api.model.ReservationDto;
import java.time.LocalDate;
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
public class ReservationIntegrationTest extends IntegrationTestBase {

  private final String ENDPOINT = "/reservation";

  @Test
  public void thatReservationCanBeRetrievedById() throws JSONException {
    final int id = 4;

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + id),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"id\":4,\"book\":{\"id\":5,\"name\":\"Vývoj ľudstva\",\"description\":\"Antropologická kniha\",\"author\":\"Doktor Sova\",\"publisher\":\"IKAR\"},"
            + "\"customer\":{\"id\":5,\"name\":\"Anton Tono\",\"address\":\"Letná 4, Košice\",\"postal\":\"04001\",\"phone\":\"+421658333542\",\"email\":\"antono@gmail.com\"},"
            + "\"start\":\"2024-10-01\",\"end\":\"2024-10-01\"}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatAllReservationsCanBeRetrieved() throws JSONException {
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"content\":[{\"id\":1,\"book\":{\"id\":2,\"name\":\"GameDev\",\"description\":\"Game development tutorials\",\"author\":\"Rado Tušan\",\"publisher\":\"IKAR\"},"
            + "\"customer\":{\"id\":3,\"name\":\"Mišo Fecenko\",\"address\":\"Humenská 5, Humenné\",\"postal\":\"66101\",\"email\":\"m.fecenko@gmail.com\"},\"start\":\"2024-10-01\",\"end\":\"2024-10-01\"},"
            + "{\"id\":2,\"book\":{\"id\":3,\"name\":\"Rýchle šípy\",\"author\":\"Jaroslav Foglár\",\"publisher\":\"ČSSR\"},"
            + "\"customer\":{\"id\":2,\"name\":\"Dávid Pinďo\",\"address\":\"Bernoláková 14, Košice\",\"postal\":\"04311\",\"phone\":\"+421668457323\"},\"start\":\"2024-10-01\",\"end\":\"2024-10-01\"},"
            + "{\"id\":3,\"book\":{\"id\":4,\"name\":\"Šachy\",\"author\":\"Peťo šachmajster\"},"
            + "\"customer\":{\"id\":1,\"name\":\"František Feri\",\"address\":\"Zámocká 3, Košice\",\"postal\":\"04001\"},\"start\":\"2024-10-01\",\"end\":\"2024-10-01\"},"
            + "{\"id\":4,\"book\":{\"id\":5,\"name\":\"Vývoj ľudstva\",\"description\":\"Antropologická kniha\",\"author\":\"Doktor Sova\",\"publisher\":\"IKAR\"},"
            + "\"customer\":{\"id\":5,\"name\":\"Anton Tono\",\"address\":\"Letná 4, Košice\",\"postal\":\"04001\",\"phone\":\"+421658333542\",\"email\":\"antono@gmail.com\"},\"start\":\"2024-10-01\",\"end\":\"2024-10-01\"}],"
            + "\"pageable\":{\"pageNumber\":0,\"pageSize\":20,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},"
            + "\"totalElements\":4,\"totalPages\":1,\"last\":true,\"size\":20,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":4,\"first\":true,\"empty\":false}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatReservationCanBeCreated() throws JsonProcessingException {
    final int bookId = 1;
    final int customerId = 2;
    final LocalDate start = getFormattedDateTime(LocalDate.now());
    final LocalDate end = getFormattedDateTime(LocalDate.now()).plusDays(7);

    BookDto book = new BookDto();
    book.setId(bookId);

    CustomerDto customer = new CustomerDto();
    customer.setId(customerId);

    ReservationDto newReservation = new ReservationDto(book, customer, start, end);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newReservation, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    int responseBook = objectMapper.readTree(response.getBody()).path("book").path("id").asInt();
    int responseCustomer =
        objectMapper.readTree(response.getBody()).path("customer").path("id").asInt();
    String responseStart = objectMapper.readTree(response.getBody()).path("start").asText();
    String responseEnd = objectMapper.readTree(response.getBody()).path("end").asText();

    assertEquals(bookId, responseBook);
    assertEquals(customerId, responseCustomer);
    assertEquals(start.toString(), responseStart);
    assertEquals(end.toString(), responseEnd);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatReservationCanBeCreatedAndUpdated() throws JsonProcessingException {
    final int bookId = 1;
    final int customerId = 2;
    final LocalDate start = getFormattedDateTime(LocalDate.now());
    final LocalDate end = getFormattedDateTime(LocalDate.now()).plusDays(7);

    BookDto book = new BookDto();
    book.setId(bookId);

    CustomerDto customer = new CustomerDto();
    customer.setId(customerId);

    ReservationDto newReservation = new ReservationDto(book, customer, start, end);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newReservation, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    int responseBook = objectMapper.readTree(response.getBody()).path("book").path("id").asInt();
    int responseCustomer =
        objectMapper.readTree(response.getBody()).path("customer").path("id").asInt();
    String responseStart = objectMapper.readTree(response.getBody()).path("start").asText();
    String responseEnd = objectMapper.readTree(response.getBody()).path("end").asText();

    assertEquals(bookId, responseBook);
    assertEquals(customerId, responseCustomer);
    assertEquals(start.toString(), responseStart);
    assertEquals(end.toString(), responseEnd);

    final int updatedBook = 2;
    final int updatedCustomer = 3;
    final LocalDate updatedStart = getFormattedDateTime(LocalDate.now()).plusDays(1);
    final LocalDate updatedEnd = getFormattedDateTime(LocalDate.now()).plusDays(3);

    book.setId(updatedBook);
    customer.setId(updatedCustomer);

    ReservationDto updatedReservation =
        new ReservationDto(book, customer, updatedStart, updatedEnd);

    ResponseEntity<String> updatResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.PUT,
            new HttpEntity<>(updatedReservation, headers),
            String.class);

    int updatedResponseBook =
        objectMapper.readTree(updatResponse.getBody()).path("book").path("id").asInt();
    int updatedResponseCustomer =
        objectMapper.readTree(updatResponse.getBody()).path("customer").path("id").asInt();
    String updatedResponseStart =
        objectMapper.readTree(updatResponse.getBody()).path("start").asText();
    String updatedResponseEnd = objectMapper.readTree(updatResponse.getBody()).path("end").asText();

    assertEquals(updatedBook, updatedResponseBook);
    assertEquals(updatedCustomer, updatedResponseCustomer);
    assertEquals(updatedStart.toString(), updatedResponseStart);
    assertEquals(updatedEnd.toString(), updatedResponseEnd);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatReservationCanBeCreatedAndDeleted() throws JsonProcessingException {
    final int bookId = 1;
    final int customerId = 2;
    final LocalDate start = getFormattedDateTime(LocalDate.now());
    final LocalDate end = getFormattedDateTime(LocalDate.now()).plusDays(7);

    BookDto book = new BookDto();
    book.setId(bookId);

    CustomerDto customer = new CustomerDto();
    customer.setId(customerId);

    ReservationDto newReservation = new ReservationDto(book, customer, start, end);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newReservation, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    int responseBook = objectMapper.readTree(response.getBody()).path("book").path("id").asInt();
    int responseCustomer =
        objectMapper.readTree(response.getBody()).path("customer").path("id").asInt();
    String responseStart = objectMapper.readTree(response.getBody()).path("start").asText();
    String responseEnd = objectMapper.readTree(response.getBody()).path("end").asText();

    assertEquals(bookId, responseBook);
    assertEquals(customerId, responseCustomer);
    assertEquals(start.toString(), responseStart);
    assertEquals(end.toString(), responseEnd);

    ResponseEntity<String> deleteResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.DELETE,
            new HttpEntity<>(null, headers),
            String.class);

    assertEquals(deleteResponse.getStatusCode(), HttpStatusCode.valueOf(204));
  }
}
