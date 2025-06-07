package co.id.bca.cros.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionConstant {
    public static final String LOGIN_SESSION_ID_COLUMN = "{loginSessionId}";
    public static final String SESSION_UIDM_DATA = "UIDM_data_" + LOGIN_SESSION_ID_COLUMN;
}
