package co.id.bca.cros.shared.middleware.security;

import co.id.bca.cros.module.auth.dto.AuthLoginResponseDto;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.helper.apibody.ApiBodyHelper;
import co.id.bca.cros.shared.helper.auth.AuthHelper;
import co.id.bca.cros.shared.helper.errorschema.ErrorSchemaHelper;
import co.id.bca.cros.shared.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.ZonedDateTime;

@Component
@Slf4j
public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (skipFilter(request, response, filterChain))
                return;

            AuthHelper authHelper = new AuthHelper();
            CommonUtil commonUtil = new CommonUtil();

            val sessionId = authHelper.getSessionIdFromHeader(request);

            val session = request.getSession(false);
            if (session != null && session.getAttribute(commonUtil.sessionConstantReplacer(sessionId)) != null) {
                val uidmData = (AuthLoginResponseDto) session.getAttribute(commonUtil.sessionConstantReplacer(sessionId));

                if (ZonedDateTime.now().isBefore(uidmData.getExpiredLogin())) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new CustomErrorException(Errors.UNAUTHORIZED, "Login expired.");
                }
            } else {
                throw new CustomErrorException(Errors.UNAUTHORIZED, "Need to be authenticated.");
            }
        } catch (CustomErrorException e) {
            val apiBodyResponse = new ApiBodyHelper(new ErrorSchemaHelper()).create(Errors.UNAUTHORIZED);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiBodyResponse));
            response.getWriter().flush();
        }
    }

    private boolean skipFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request.getRequestURI().startsWith("/swagger-ui") || request.getRequestURI().startsWith("/v3/api-docs") ||
                request.getRequestURI().startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);

            return true;
        }

        return false;
    }
}
