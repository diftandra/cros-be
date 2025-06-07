package co.id.bca.cros.shared.helper.auth;

import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthHelper {
    public String getSessionIdFromHeader(HttpServletRequest request) {
        val authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new CustomErrorException(Errors.UNAUTHORIZED, "Need to be authenticated.");
        }
    }
}
