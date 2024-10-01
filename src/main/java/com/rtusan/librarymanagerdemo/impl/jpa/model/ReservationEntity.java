package com.rtusan.librarymanagerdemo.impl.jpa.model;

import com.rtusan.librarymanagerdemo.shared.EntityBase;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "reservation", schema = "public")
@Comment(value = "Entity representing reservation.")
@SequenceGenerator(
    name = "reservation_seq_generator",
    sequenceName = "reservation_id_seq",
    allocationSize = 1)
public class ReservationEntity extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq_generator")
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "book", nullable = false)
  private BookEntity book;

  @ManyToOne
  @JoinColumn(name = "customer", nullable = false)
  private CustomerEntity customer;

  @Column(name = "res_start")
  private LocalDate start;

  @Column(name = "res_end")
  private LocalDate end;
}
