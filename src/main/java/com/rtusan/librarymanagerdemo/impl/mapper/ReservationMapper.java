package com.rtusan.librarymanagerdemo.impl.mapper;

import com.rtusan.librarymanagerdemo.api.model.ReservationDto;
import com.rtusan.librarymanagerdemo.impl.jpa.model.ReservationEntity;
import com.rtusan.librarymanagerdemo.shared.MapperBase;
import com.rtusan.librarymanagerdemo.shared.MapperConfig;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, builder = @Builder(disableBuilder = true))
public interface ReservationMapper extends MapperBase<ReservationDto, ReservationEntity> {}
