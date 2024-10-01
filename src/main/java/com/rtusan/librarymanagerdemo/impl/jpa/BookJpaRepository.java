package com.rtusan.librarymanagerdemo.impl.jpa;

import com.rtusan.librarymanagerdemo.impl.jpa.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Integer> {}
