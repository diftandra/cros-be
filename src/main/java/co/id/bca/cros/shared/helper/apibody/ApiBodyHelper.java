package co.id.bca.cros.shared.helper.apibody;

import co.id.bca.cros.shared.dto.ApiBody;
import co.id.bca.cros.shared.dto.ErrorSchema;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.helper.errorschema.ErrorSchemaHelper;
import org.springframework.stereotype.Service;

@Service
public class ApiBodyHelper {
    private final ErrorSchemaHelper errorSchemaHelper;

    public ApiBodyHelper(ErrorSchemaHelper errorSchemaHelper) {
        this.errorSchemaHelper = errorSchemaHelper;
    }

    public <T> ApiBody<T> create(ErrorSchema errorSchema, T output) {
        return new ApiBody<>(errorSchema, output);
    }

    public <T> ApiBody<T> create(T output) {
        return create(errorSchemaHelper.success(), output);
    }

    public <T> ApiBody<T> create(Errors error) {
        return create(errorSchemaHelper.details(error), null);
    }

    public <T> ApiBody<T> create(CustomErrorException exception) {
        Errors error = isValidErrors(exception.getError()) ? exception.getError() : Errors.INTERNAL_SERVER_ERROR;
        return create(errorSchemaHelper.details(error), null);
    }

    private boolean isValidErrors(Errors error) {
        if (error == null) {
            return false;
        }

        for (Errors e : Errors.values()) {
            if (e == error) {
                return true;
            }
        }

        return false;
    }

    public <T> ApiBody<T> empty() {
        return create(errorSchemaHelper.success(), null);
    }
}
