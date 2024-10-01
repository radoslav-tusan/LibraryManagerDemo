package com.rtusan.librarymanagerdemo.impl.jpa.model;

import com.rtusan.librarymanagerdemo.shared.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "book", schema = "public")
@Comment(value = "Entity representing book.")
@SequenceGenerator(name = "book_seq_generator", sequenceName = "book_id_seq", allocationSize = 1)
public class BookEntity extends EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Size(max = 50)
  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Size(max = 500)
  @Column(name = "description", length = 500)
  private String description;

  @Size(max = 50)
  @Column(name = "author", length = 50, nullable = false)
  private String author;

  @Size(max = 100)
  @Column(name = "publisher", length = 100)
  private String publisher;
}
