package com.rtusan.librarymanagerdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtusan.librarymanagerdemo.api.model.CustomerDto;
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
public class CustomerIntegrationTest extends IntegrationTestBase {

  private final String ENDPOINT = "/customer";

  @Test
  public void thatCustomerCanBeRetrievedById() throws JSONException {
    final int id = 5;

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + id),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"id\":5,\"name\":\"Anton Tono\",\"address\":\"Letná 4, Košice\",\"postal\":\"04001\",\"phone\":\"+421658333542\",\"email\":\"antono@gmail.com\"}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatAllCustomersCanBeRetrieved() throws JSONException {
    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class);

    final String expected =
        "{\"content\":[{\"id\":1,\"name\":\"František Feri\",\"address\":\"Zámocká 3, Košice\",\"postal\":\"04001\"},"
            + "{\"id\":2,\"name\":\"Dávid Pinďo\",\"address\":\"Bernoláková 14, Košice\",\"postal\":\"04311\",\"phone\":\"+421668457323\"},"
            + "{\"id\":3,\"name\":\"Mišo Fecenko\",\"address\":\"Humenská 5, Humenné\",\"postal\":\"66101\",\"email\":\"m.fecenko@gmail.com\"},"
            + "{\"id\":4,\"name\":\"Ján Zemčák\",\"address\":\"Rudá 55, Rudník\",\"postal\":\"65438\"},"
            + "{\"id\":5,\"name\":\"Anton Tono\",\"address\":\"Letná 4, Košice\",\"postal\":\"04001\",\"phone\":\"+421658333542\",\"email\":\"antono@gmail.com\"}],"
            + "\"pageable\":{\"pageNumber\":0,\"pageSize\":20,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},"
            + "\"last\":true,\"totalElements\":5,\"totalPages\":1,\"first\":true,\"numberOfElements\":5,\"size\":20,\"number\":0,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"empty\":false}";

    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void thatCustomerCanBeCreated() throws JsonProcessingException {
    final String name = "New Name";
    final String address = "New Address";
    final String postal = "04001";
    final String phone = "+421666888";
    final String mail = "new.mail@gmail.com";

    CustomerDto newCustomer = new CustomerDto(name, address, postal, phone, mail);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newCustomer, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseAddress = objectMapper.readTree(response.getBody()).path("address").asText();
    String responsePostal = objectMapper.readTree(response.getBody()).path("postal").asText();
    String responsePhone = objectMapper.readTree(response.getBody()).path("phone").asText();
    String responseMail = objectMapper.readTree(response.getBody()).path("email").asText();

    assertEquals(name, responseName);
    assertEquals(address, responseAddress);
    assertEquals(postal, responsePostal);
    assertEquals(phone, responsePhone);
    assertEquals(mail, responseMail);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatCustomerCanBeCreatedAndUpdated() throws JsonProcessingException {
    final String name = "New Name";
    final String address = "New Address";
    final String postal = "04001";
    final String phone = "+421666888";
    final String mail = "new.mail@gmail.com";

    CustomerDto newCustomer = new CustomerDto(name, address, postal, phone, mail);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newCustomer, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseAddress = objectMapper.readTree(response.getBody()).path("address").asText();
    String responsePostal = objectMapper.readTree(response.getBody()).path("postal").asText();
    String responsePhone = objectMapper.readTree(response.getBody()).path("phone").asText();
    String responseMail = objectMapper.readTree(response.getBody()).path("email").asText();

    assertEquals(name, responseName);
    assertEquals(address, responseAddress);
    assertEquals(postal, responsePostal);
    assertEquals(phone, responsePhone);
    assertEquals(mail, responseMail);

    final String updatedName = "Updated Name";
    final String updatedAddress = "Updated Address";
    final String updatedPostal = "04005";
    final String updatedPhone = "+421333555";
    final String updatedMail = "updated.mail@gmail.com";

    CustomerDto updatedCustomer =
        new CustomerDto(updatedName, updatedAddress, updatedPostal, updatedPhone, updatedMail);

    ResponseEntity<String> updatResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.PUT,
            new HttpEntity<>(updatedCustomer, headers),
            String.class);

    String updatedResponseName =
        objectMapper.readTree(updatResponse.getBody()).path("name").asText();
    String updatedResponseDesc =
        objectMapper.readTree(updatResponse.getBody()).path("address").asText();
    String updatedResponseAuthor =
        objectMapper.readTree(updatResponse.getBody()).path("postal").asText();
    String updatedResponsePublisher =
        objectMapper.readTree(updatResponse.getBody()).path("phone").asText();
    String updatedResponseMail =
        objectMapper.readTree(updatResponse.getBody()).path("email").asText();

    assertEquals(updatedName, updatedResponseName);
    assertEquals(updatedAddress, updatedResponseDesc);
    assertEquals(updatedPostal, updatedResponseAuthor);
    assertEquals(updatedPhone, updatedResponsePublisher);
    assertEquals(updatedMail, updatedResponseMail);

    // Clear the created record
    restTemplate.exchange(
        createURL(port, ENDPOINT + "/" + responseId),
        HttpMethod.DELETE,
        new HttpEntity<>(null, headers),
        String.class);
  }

  @Test
  public void thatCustomerCanBeCreatedAndDeleted() throws JsonProcessingException {
    final String name = "New Name";
    final String address = "New Address";
    final String postal = "04001";
    final String phone = "+421666888";
    final String mail = "new.mail@gmail.com";

    CustomerDto newCustomer = new CustomerDto(name, address, postal, phone, mail);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURL(port, ENDPOINT),
            HttpMethod.POST,
            new HttpEntity<>(newCustomer, headers),
            String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    String responseId = objectMapper.readTree(response.getBody()).path("id").asText();
    String responseName = objectMapper.readTree(response.getBody()).path("name").asText();
    String responseAddress = objectMapper.readTree(response.getBody()).path("address").asText();
    String responsePostal = objectMapper.readTree(response.getBody()).path("postal").asText();
    String responsePhone = objectMapper.readTree(response.getBody()).path("phone").asText();
    String responseMail = objectMapper.readTree(response.getBody()).path("email").asText();

    assertEquals(name, responseName);
    assertEquals(address, responseAddress);
    assertEquals(postal, responsePostal);
    assertEquals(phone, responsePhone);
    assertEquals(mail, responseMail);

    ResponseEntity<String> deleteResponse =
        restTemplate.exchange(
            createURL(port, ENDPOINT + "/" + responseId),
            HttpMethod.DELETE,
            new HttpEntity<>(null, headers),
            String.class);

    assertEquals(deleteResponse.getStatusCode(), HttpStatusCode.valueOf(204));
  }
}
