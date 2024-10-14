package model.exception;

import lombok.Data;

@Data
public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException() {
        this("Rating not found");
    }

    public RatingNotFoundException(String message) {
        super(message);
    }

    public RatingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
