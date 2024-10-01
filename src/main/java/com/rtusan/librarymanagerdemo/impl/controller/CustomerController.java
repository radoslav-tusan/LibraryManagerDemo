package com.rtusan.librarymanagerdemo.impl.controller;

import com.rtusan.librarymanagerdemo.api.CustomerApi;
import com.rtusan.librarymanagerdemo.api.model.CustomerDto;
import com.rtusan.librarymanagerdemo.impl.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {

  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @Override
  public CustomerDto create(CustomerDto dto) {
    return service.create(dto);
  }

  @Override
  public CustomerDto update(int id, CustomerDto update) {
    return service.update(id, update);
  }

  @Override
  public CustomerDto findById(int id) {
    return service.getById(id);
  }

  @Override
  public Page<CustomerDto> findAll(Pageable pageable) {
    return service.getAll(pageable);
  }

  @Override
  public void delete(int id) {
    service.delete(id);
  }
}
