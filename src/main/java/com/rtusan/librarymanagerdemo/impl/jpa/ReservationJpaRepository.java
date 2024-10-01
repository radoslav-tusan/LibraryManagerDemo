package com.rtusan.librarymanagerdemo.impl.jpa;

import com.rtusan.librarymanagerdemo.impl.jpa.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Integer> {}
