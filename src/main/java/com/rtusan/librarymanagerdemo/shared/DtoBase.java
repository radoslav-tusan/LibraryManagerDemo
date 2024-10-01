package com.rtusan.librarymanagerdemo.shared;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class DtoBase {

  private Integer id;
}
