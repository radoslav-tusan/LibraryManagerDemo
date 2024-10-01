package com.rtusan.librarymanagerdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

public class IntegrationTestBase {

  private final String LOCAL_URL = "http://localhost:";

  @LocalServerPort protected int port;

  protected TestRestTemplate restTemplate = new TestRestTemplate();

  protected HttpHeaders headers = new HttpHeaders();

  protected String createURL(int port, String uri) {
    return LOCAL_URL + port + uri;
  }

  protected LocalDate getFormattedDateTime(LocalDate localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDateTime = localDate.format(formatter);
    return LocalDate.parse(formattedDateTime, formatter);
  }
}
