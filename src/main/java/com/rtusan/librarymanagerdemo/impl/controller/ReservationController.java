package com.rtusan.librarymanagerdemo.impl.controller;

import com.rtusan.librarymanagerdemo.api.ReservationApi;
import com.rtusan.librarymanagerdemo.api.model.ReservationDto;
import com.rtusan.librarymanagerdemo.impl.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController implements ReservationApi {

  private final ReservationService service;

  public ReservationController(ReservationService service) {
    this.service = service;
  }

  @Override
  public ReservationDto create(ReservationDto dto) {
    return service.create(dto);
  }

  @Override
  public ReservationDto update(int id, ReservationDto update) {
    return service.update(id, update);
  }

  @Override
  public ReservationDto findById(int id) {
    return service.getById(id);
  }

  @Override
  public Page<ReservationDto> findAll(Pageable pageable) {
    return service.getAll(pageable);
  }

  @Override
  public void delete(int id) {
    service.delete(id);
  }
}
