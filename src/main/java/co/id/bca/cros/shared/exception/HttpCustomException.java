package co.id.bca.cros.shared.exception;

import co.id.bca.cros.shared.dto.ErrorSchema;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class HttpCustomException extends RuntimeException {
    private final HttpStatusCode httpStatusCode;
    private final transient ErrorSchema errorSchema;

    public HttpCustomException(HttpStatusCode httpStatusCode, ErrorSchema errorSchema, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.errorSchema = errorSchema;
    }
}
