package co.id.bca.cros.shared.helper.errorschema;

import co.id.bca.cros.shared.dto.ErrorSchema;
import co.id.bca.cros.shared.enumeration.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorSchemaHelper {
    private final ErrorSchema errorSchema;

    public ErrorSchemaHelper() {
        this.errorSchema = new ErrorSchema();
    }

    public ErrorSchema details(Errors errors) {
        errorSchema.setErrorCode(errors.getCode());
        errorSchema.setErrorMessage(new ErrorSchema.ErrorMessage(errors.toString(), errors.toIndonesian()));

        return errorSchema;
    }

    public ErrorSchema success() {
        errorSchema.setErrorCode(Errors.SUCCESS.getCode());
        errorSchema.setErrorMessage(new ErrorSchema.ErrorMessage(Errors.SUCCESS.toString(), Errors.SUCCESS.toIndonesian()));

        return errorSchema;
    }
}
