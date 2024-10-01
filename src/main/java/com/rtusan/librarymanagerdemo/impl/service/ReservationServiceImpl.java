package com.rtusan.librarymanagerdemo.impl.service;

import com.rtusan.librarymanagerdemo.api.model.ReservationDto;
import com.rtusan.librarymanagerdemo.impl.jpa.model.ReservationEntity;
import com.rtusan.librarymanagerdemo.shared.MapperBase;
import com.rtusan.librarymanagerdemo.shared.ServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservationServiceImpl extends ServiceImpl<ReservationDto, ReservationEntity>
    implements ReservationService {

  public ReservationServiceImpl(
      JpaRepository<ReservationEntity, Integer> jpaRepository,
      MapperBase<ReservationDto, ReservationEntity> mapper) {
    super(jpaRepository, mapper);
  }
}
