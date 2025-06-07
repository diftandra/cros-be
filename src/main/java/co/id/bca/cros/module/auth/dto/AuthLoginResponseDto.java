package co.id.bca.cros.module.auth.dto;

import co.id.bca.cros.module.auth.uidm.dto.login.LoggedUserDetailDto;
import co.id.bca.cros.module.auth.uidm.dto.login.LoggedUserRolesDto;
import co.id.bca.cros.module.auth.uidm.dto.login.LoginDetailDto;
import co.id.bca.cros.module.auth.uidm.dto.userfeature.UserFeatureOutputSchemaDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthLoginResponseDto {
    private LoginDetailDto loginDetail;
    private LoggedUserDetailDto loggedUserDetail;
    private List<LoggedUserRolesDto> loggedUserRoles;
    private UserFeatureOutputSchemaDto listOfUserFeatures;
    private ZonedDateTime expiredLogin;
}
