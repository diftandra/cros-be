package co.id.bca.cros.module.auth.uidm.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoggedUserDetailDto {
    private String userId;
    private String userName;
    private String employeeIdNumber;
    private String adUserName;
    private String superiorId;
    private String superiorName;
    private String email;
    private String roleCode;
    private String officeCode;
    private String officeName;
    private String deptCode;
    private String deptName;
    private String subDeptCode;
    private String subDeptName;
    private String jobTitleCode;
    private String jobTitleName;
    private String deptSapCode;
    private String deptSapName;
    private String subDeptSapCode;
    private String subDeptSapName;
    private String jobTitleSapCode;
    private String jobTitleSapName;
    private String appCode;
    private Boolean userLoggedIn;
    private Integer wrongPassword;
    private Boolean userLocked;
    private String activeFrom;
    private String activeTo;
    private Boolean active;
    private Long dateLastLogin;
    private Long dateLastLogout;
    private String dateLastLoginFmt;
    private String dateLastLogoutFmt;
    private String officerCode;
    private String mobileNo;
    private String initial;
    private String dateLastLocked;
    @JsonProperty("date_last_OTP_succeed")
    private String dateLastOTPSucceed;
    private String dateLastLockedFmt;
    @JsonProperty("date_last_OTP_succeed_fmt")
    private String dateLastOTPSucceedFmt;
    @JsonProperty("is_valid_UID")
    private Boolean isValidUID;
    @JsonProperty("is_need_resend_OTP")
    private Boolean isNeedResendOTP;
    private Boolean isPasswordRegistered;
    private String additionalField;
    private String accessAdditionalField;
    private String userType;
}
