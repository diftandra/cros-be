package co.id.bca.cros.shared.exception;

import co.id.bca.cros.shared.dto.ApiBody;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.helper.apibody.ApiBodyHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ApiBodyHelper apiBodyHelper;

    public GlobalExceptionHandler(ApiBodyHelper apiBodyHelper) {
        this.apiBodyHelper = apiBodyHelper;
    }

    @ExceptionHandler
    public ResponseEntity<ApiBody<?>> handleAllException(Throwable exception) {
        ApiBody<?> apiBody;
        if (exception instanceof CustomErrorException customError) {
            apiBody = apiBodyHelper.create(customError);
            log.error("Exception thrown : %s".formatted(customError.getMessage()), customError);
            return new ResponseEntity<>(apiBody, HttpStatus.OK);
        } else {
            apiBody = apiBodyHelper.create(Errors.GENERAL_ERROR);
            log.error("Exception thrown : %s".formatted(exception.getMessage()), exception);
            return new ResponseEntity<>(apiBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
