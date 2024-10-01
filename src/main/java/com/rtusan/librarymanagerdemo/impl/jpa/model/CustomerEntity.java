package com.rtusan.librarymanagerdemo.impl.jpa.model;

import com.rtusan.librarymanagerdemo.shared.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "public")
@Comment(value = "Entity representing customer.")
@SequenceGenerator(
    name = "customer_seq_generator",
    sequenceName = "customer_id_seq",
    allocationSize = 1)
public class CustomerEntity extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_generator")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Size(max = 50)
  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Size(max = 100)
  @Column(name = "address", length = 100)
  private String address;

  @Size(min = 5, max = 6)
  @Column(name = "postal", length = 6)
  @Pattern(
      regexp = "^\\d{3}\\s?\\d{2}$",
      message = "Enter slovak postal code (with or without space)")
  private String postal;

  @Column(name = "phone")
  @Pattern(
      regexp = "^\\+421[1-9][0-9]{8}$",
      message = "Enter valid slovak number starting with +421")
  private String phone;

  @Email(message = "Enter valid email address")
  private String email;
}
