package model.exception;

import lombok.Data;

@Data
public class ThingNotFoundException extends RuntimeException {
    public ThingNotFoundException() {
        this("Thing not found");
    }

    public ThingNotFoundException(String message) {
        super(message);
    }

    public ThingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
