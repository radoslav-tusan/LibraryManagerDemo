package com.rtusan.librarymanagerdemo.shared;

import lombok.Getter;

@Getter
public enum ErrorCode {
  ENTITY_NOT_FOUND("Entity not found."),
  BAD_REQUEST("Bad request."),
  UNEXPECTED_RESPONSE("Unexpected response."),
  CONVERTER_ERROR("Failed to convert entity."),
  DELETE_FAILED("Deletion of entity failed."),
  CONFLICT("Entity conflict"),
  NOT_IMPLEMENTED_ERROR("Not implemented.");

  private final String message;

  ErrorCode(final String message) {
    this.message = message;
  }
}
