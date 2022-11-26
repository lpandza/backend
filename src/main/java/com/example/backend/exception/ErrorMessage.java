package com.example.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
  private static final long serialVersionUID = 0;
  private final Throwable cause;
  private final String message;
  public ErrorMessage(String message) {
    this(message, null);
  }

  public ErrorMessage(String message, Throwable cause) {
    this.message = message;
    this.cause = cause;
  }


}
