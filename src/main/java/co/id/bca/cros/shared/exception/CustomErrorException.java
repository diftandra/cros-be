package co.id.bca.cros.shared.exception;

import co.id.bca.cros.shared.enumeration.Errors;
import lombok.Getter;

@Getter
public class CustomErrorException extends RuntimeException {
    private final Errors error;

    public CustomErrorException(Errors error, String message) {
        super(message);
        this.error = error;
    }

    public CustomErrorException(Errors error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }
}
