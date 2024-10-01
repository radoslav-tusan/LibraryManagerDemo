package com.rtusan.librarymanagerdemo.shared;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public interface MapperBase<DTO extends DtoBase, ENTITY extends EntityBase> {

  ENTITY toEntity(DTO dto);

  DTO toDto(ENTITY entity);

  default List<DTO> toDtoList(List<ENTITY> entities) {
    return entities.stream().map(this::toDto).collect(Collectors.toList());
  }

  default Page<DTO> toDtoList(Page<ENTITY> entities) {
    return entities.map(this::toDto);
  }
}
