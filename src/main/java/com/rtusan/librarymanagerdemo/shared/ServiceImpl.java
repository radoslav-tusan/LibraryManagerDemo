package com.rtusan.librarymanagerdemo.shared;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

public abstract class ServiceImpl<DTO extends DtoBase, ENTITY extends EntityBase>
    implements ServiceBase<DTO, Integer> {

  private final JpaRepository<ENTITY, Integer> jpaRepository;
  private final MapperBase<DTO, ENTITY> mapper;

  protected ServiceImpl(
      JpaRepository<ENTITY, Integer> jpaRepository, MapperBase<DTO, ENTITY> mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
  }

  @Override
  public DTO create(DTO dto) {
    ENTITY entity = mapper.toEntity(dto);

    return mapper.toDto(jpaRepository.save(entity));
  }

  @Override
  public DTO update(Integer id, DTO dto) {
    dto.setId(id);

    return mapper.toDto(jpaRepository.save(mapper.toEntity(dto)));
  }

  @Override
  public Page<DTO> getAll(Pageable pageable) {
    return mapper.toDtoList(jpaRepository.findAll(pageable));
  }

  @Override
  public DTO getById(Integer id) throws ServiceException {
    return jpaRepository
        .findById(id)
        .map(mapper::toDto)
        .orElseThrow(
            () ->
                new ServiceException(
                    ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .message("Resource with the given ID was not found.")
                        .details(Map.of("id", id.toString()))
                        .build()));
  }

  @Override
  public DTO findById(Integer id) {
    return jpaRepository.findById(id).map(mapper::toDto).orElse(null);
  }

  @Override
  public void delete(Integer id) {
    jpaRepository.deleteById(id);
  }
}
