package com.rtusan.librarymanagerdemo.impl.controller;

import com.rtusan.librarymanagerdemo.api.BookApi;
import com.rtusan.librarymanagerdemo.api.model.BookDto;
import com.rtusan.librarymanagerdemo.impl.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController implements BookApi {

  private final BookService service;

  public BookController(BookService service) {
    this.service = service;
  }

  @Override
  public BookDto create(BookDto dto) {
    return service.create(dto);
  }

  @Override
  public BookDto update(int id, BookDto update) {
    return service.update(id, update);
  }

  @Override
  public BookDto findById(int id) {
    return service.getById(id);
  }

  @Override
  public Page<BookDto> findAll(Pageable pageable) {
    return service.getAll(pageable);
  }

  @Override
  public void delete(int id) {
    service.delete(id);
  }
}
