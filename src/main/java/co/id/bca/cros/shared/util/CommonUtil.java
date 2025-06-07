package co.id.bca.cros.shared.util;

import co.id.bca.cros.shared.constant.SessionConstant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class CommonUtil {
    public String sessionConstantReplacer(String userId) {
        return SessionConstant.SESSION_UIDM_DATA.replace(SessionConstant.LOGIN_SESSION_ID_COLUMN, userId);
    }

    public BigDecimal bigDecimalNullChecker(BigDecimal value) {
        return value == null ? new BigDecimal(BigInteger.ZERO) : value;
    }
}
