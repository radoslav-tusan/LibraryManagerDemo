package com.rtusan.librarymanagerdemo.impl.jpa;

import com.rtusan.librarymanagerdemo.impl.jpa.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {}
