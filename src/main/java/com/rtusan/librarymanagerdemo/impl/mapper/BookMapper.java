package com.rtusan.librarymanagerdemo.impl.mapper;

import com.rtusan.librarymanagerdemo.api.model.BookDto;
import com.rtusan.librarymanagerdemo.impl.jpa.model.BookEntity;
import com.rtusan.librarymanagerdemo.shared.MapperBase;
import com.rtusan.librarymanagerdemo.shared.MapperConfig;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, builder = @Builder(disableBuilder = true))
public interface BookMapper extends MapperBase<BookDto, BookEntity> {}
