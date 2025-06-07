package co.id.bca.cros.module.auth.uidm.dto.login;

import co.id.bca.cros.shared.dto.ErrorSchema;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginOutputSchemaDto {
    private LoginDetailDto loginDetail;
    private LoggedUserDetailDto loggedUserDetail;
    private List<LoggedUserRolesDto> loggedUserRoles;
    private LoginOutputSchemaDto outputSchema;
    private ErrorSchema errorSchema;
}
