package model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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
