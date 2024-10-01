package com.rtusan.librarymanagerdemo.api;

import com.rtusan.librarymanagerdemo.api.model.ReservationDto;
import com.rtusan.librarymanagerdemo.shared.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservation", description = "Endpoint for manipulating with reservation resource")
@Validated
public interface ReservationApi {
  @Operation(
      operationId = "createReservation",
      summary = "Creates new reservation record",
      responses = {
        @ApiResponse(responseCode = "201", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", content = @Content),
        @ApiResponse(responseCode = "403", content = @Content),
        @ApiResponse(responseCode = "404", content = @Content),
        @ApiResponse(
            description = "Unexpected error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
      })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  ReservationDto create(@Valid @RequestBody ReservationDto dto);

  @Operation(
      operationId = "updateReservation",
      summary = "Updates reservation record",
      responses = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", content = @Content),
        @ApiResponse(responseCode = "403", content = @Content),
        @ApiResponse(responseCode = "404", content = @Content),
        @ApiResponse(
            description = "Unexpected error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
      })
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  ReservationDto update(@PathVariable int id, @Valid @RequestBody ReservationDto update);

  @Operation(
      operationId = "getReservationById",
      summary = "Returns reservation record by record id",
      responses = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", content = @Content),
        @ApiResponse(responseCode = "403", content = @Content),
        @ApiResponse(responseCode = "404", content = @Content),
        @ApiResponse(
            description = "Unexpected error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
      })
  @GetMapping(value = "/{id}")
  ReservationDto findById(@PathVariable int id);

  @Operation(
      operationId = "getAllReservationItems",
      summary = "Returns all reservation records",
      responses = {
        @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "401", content = @Content),
        @ApiResponse(responseCode = "403", content = @Content),
        @ApiResponse(responseCode = "404", content = @Content),
        @ApiResponse(
            description = "Unexpected error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
      })
  @GetMapping()
  Page<ReservationDto> findAll(@ParameterObject Pageable pageable);

  @Operation(
      operationId = "deleteReservation",
      summary = "Deletes reservation record",
      responses = {
        @ApiResponse(responseCode = "204"),
        @ApiResponse(responseCode = "401", content = @Content),
        @ApiResponse(responseCode = "403", content = @Content),
        @ApiResponse(responseCode = "404", content = @Content),
        @ApiResponse(
            description = "Unexpected error",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
      })
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void delete(@PathVariable int id);
}
