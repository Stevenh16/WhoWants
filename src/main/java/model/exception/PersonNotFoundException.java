package model.exception;

import lombok.Data;

@Data
public class PersonNotFoundException extends RuntimeException {
  public PersonNotFoundException() {
    this("Person not found");
  }

  public PersonNotFoundException(String message) {
    super(message);
  }

  public PersonNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
