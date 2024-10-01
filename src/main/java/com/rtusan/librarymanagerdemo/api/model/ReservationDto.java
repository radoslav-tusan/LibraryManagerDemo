package com.rtusan.librarymanagerdemo.api.model;

import com.rtusan.librarymanagerdemo.shared.DtoBase;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto extends DtoBase {

  private BookDto book;

  private CustomerDto customer;

  private LocalDate start;

  private LocalDate end;
}
