package com.rtusan.librarymanagerdemo.api.model;

import com.rtusan.librarymanagerdemo.shared.DtoBase;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends DtoBase {

  private String name;

  private String address;

  private String postal;

  private String phone;

  private String email;
}
