package com.rtusan.librarymanagerdemo.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
@Builder
public class ErrorResponse implements Serializable {

  private ErrorCode errorCode;

  private String correlationId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @Default
  private LocalDateTime timestamp = LocalDateTime.now();

  @Default private Integer status = HttpStatus.BAD_REQUEST.value();

  private String message;

  @Default private Map<String, String> details = new HashMap<>();
}
