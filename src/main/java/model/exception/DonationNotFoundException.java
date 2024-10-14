package model.exception;

import lombok.Data;

@Data
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
