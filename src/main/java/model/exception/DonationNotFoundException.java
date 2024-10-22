package model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException() {
        this("Donation not found");
    }

    public DonationNotFoundException(String message) {
        super(message);
    }

    public DonationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
