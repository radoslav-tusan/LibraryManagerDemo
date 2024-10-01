package com.rtusan.librarymanagerdemo.impl.service;

import com.rtusan.librarymanagerdemo.api.model.BookDto;
import com.rtusan.librarymanagerdemo.impl.jpa.model.BookEntity;
import com.rtusan.librarymanagerdemo.shared.MapperBase;
import com.rtusan.librarymanagerdemo.shared.ServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookServiceImpl extends ServiceImpl<BookDto, BookEntity> implements BookService {

  public BookServiceImpl(
      JpaRepository<BookEntity, Integer> jpaRepository, MapperBase<BookDto, BookEntity> mapper) {
    super(jpaRepository, mapper);
  }
}
