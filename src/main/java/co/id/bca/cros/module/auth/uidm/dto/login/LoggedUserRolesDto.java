package co.id.bca.cros.module.auth.uidm.dto.login;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoggedUserRolesDto {
    private String roleCode;
    private String roleGroupCode;
    private String userId;
    private String appCode;
    private String officeCode;
    private String roleDescription;
    private String authLimit;
    private String roleExtraCode1;
    private String roleExtraCode2;
    private String roleExtraCode3;
    private String roleExtraCode4;
    private String roleExtraCode5;
    private String roleExtraNumber1;
    private String roleExtraNumber2;
    private String roleExtraNumber3;
    private String roleExtraNumber4;
    private String roleExtraNumber5;
    private String additionalField;
}
