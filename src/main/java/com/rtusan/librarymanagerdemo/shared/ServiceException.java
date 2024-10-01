package com.rtusan.librarymanagerdemo.shared;

import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ServiceException extends RuntimeException {
  @Serial private static final long serialVersionUID = 6930501473624734213L;

  @NotNull private final ErrorResponse errorResponse;

  public ServiceException(final ErrorResponse errorResponse) {
    this(errorResponse, null);
  }

  public ServiceException(final ErrorResponse errorResponse, final Throwable cause) {
    super(cause);
    this.errorResponse = errorResponse;
  }
}
