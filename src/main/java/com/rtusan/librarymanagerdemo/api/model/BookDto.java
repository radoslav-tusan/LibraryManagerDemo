package com.rtusan.librarymanagerdemo.api.model;

import com.rtusan.librarymanagerdemo.shared.DtoBase;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto extends DtoBase {

  private String name;

  private String description;

  private String author;

  private String publisher;
}
