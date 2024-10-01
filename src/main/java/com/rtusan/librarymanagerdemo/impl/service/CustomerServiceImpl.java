package com.rtusan.librarymanagerdemo.impl.service;

import com.rtusan.librarymanagerdemo.api.model.CustomerDto;
import com.rtusan.librarymanagerdemo.impl.jpa.model.CustomerEntity;
import com.rtusan.librarymanagerdemo.shared.MapperBase;
import com.rtusan.librarymanagerdemo.shared.ServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceImpl extends ServiceImpl<CustomerDto, CustomerEntity>
    implements CustomerService {

  public CustomerServiceImpl(
      JpaRepository<CustomerEntity, Integer> jpaRepository,
      MapperBase<CustomerDto, CustomerEntity> mapper) {
    super(jpaRepository, mapper);
  }
}
