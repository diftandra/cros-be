package co.id.bca.cros.module.auth.service;

import co.id.bca.cros.module.auth.dto.AuthLoginRequestDto;
import co.id.bca.cros.module.auth.dto.AuthLoginResponseDto;
import co.id.bca.cros.module.auth.uidm.dto.login.LoggedUserDetailDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutOutputSchemaDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutPayloadDto;
import co.id.bca.cros.module.auth.uidm.service.UidmService;
import co.id.bca.cros.module.search.dto.defaultfilter.GetKanwilKancabDto;
import co.id.bca.cros.shared.constant.UserTypeConstant;
import co.id.bca.cros.shared.data.repository.CrosMasterDebiturRepository;
import co.id.bca.cros.shared.data.repository.CrosMasterKomitmenRepository;
import co.id.bca.cros.shared.data.repository.MappingKantorCabangRepository;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.helper.auth.AuthHelper;
import co.id.bca.cros.shared.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UidmService uidmService;
    private final CommonUtil commonUtil;
    private final AuthHelper authHelper;
    private final MappingKantorCabangRepository mappingKantorCabangRepository;
    private final CrosMasterDebiturRepository crosMasterDebiturRepository;
    private final CrosMasterKomitmenRepository crosMasterKomitmenRepository;

    public AuthLoginResponseDto login(AuthLoginRequestDto authLoginRequestDto, HttpServletRequest request) {
        val userId = authLoginRequestDto.getUserId();
        val password = authLoginRequestDto.getPassword();

        requestValidation(userId, password);

        val loginOutputSchema = uidmService.login(userId, password);
        val userFeatureOutputSchema = uidmService.getUserFeatures(userId);
        val loginDetailFromOutputSchema = loginOutputSchema.getLoginDetail();
        val now = ZonedDateTime.now().plusSeconds(loginDetailFromOutputSchema.getSessionTimeOut());

        LoggedUserDetailDto loggedUserDetail = loginOutputSchema.getLoggedUserDetail();
        loggedUserDetail.setUserType(UserTypeConstant.getUserTypeByOfficeCode(loggedUserDetail.getOfficeCode()));

        AuthLoginResponseDto authLoginResponseDto = new AuthLoginResponseDto();
        authLoginResponseDto.setLoginDetail(loginDetailFromOutputSchema);
        authLoginResponseDto.setLoggedUserDetail(loggedUserDetail);
        authLoginResponseDto.setLoggedUserRoles(loginOutputSchema.getLoggedUserRoles());
        authLoginResponseDto.setListOfUserFeatures(userFeatureOutputSchema);
        authLoginResponseDto.setExpiredLogin(now);

        setSession(loginDetailFromOutputSchema.getLoginSessionId(), authLoginResponseDto, request);

        return authLoginResponseDto;
    }

    public LogoutOutputSchemaDto logout(LogoutPayloadDto logoutPayload, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        httpSession.invalidate();

        return uidmService.logout(logoutPayload.getUserId());
    }

    public LoggedUserDetailDto getUserDetail(HttpServletRequest httpServletRequest) {
        val sessionId = authHelper.getSessionIdFromHeader(httpServletRequest);
        val user = (AuthLoginResponseDto) httpServletRequest.getSession().getAttribute(commonUtil.sessionConstantReplacer(sessionId));
        return user.getLoggedUserDetail();
    }

    private void requestValidation(String userId, String password) {
        if (StringUtils.isBlank(userId))
            throw new CustomErrorException(Errors.EMPTY_USER_ID, "User id is required.");

        if (StringUtils.isBlank(password))
            throw new CustomErrorException(Errors.EMPTY_PASSWORD, "Password is required.");
    }

    private void setSession(String loginSessionId, AuthLoginResponseDto loginResponse, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(commonUtil.sessionConstantReplacer(loginSessionId), loginResponse);
    }

    public void checkMasterDebiturAuthorization(LoggedUserDetailDto user, UUID idCrosMasterDebitur) {
        val userKanwilKancab = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
        val crosMasterDebitur = crosMasterDebiturRepository.findById(idCrosMasterDebitur).orElseThrow();
        checkAuthorizationByKanwilKancab(
                user.getUserType(),
                userKanwilKancab,
                crosMasterDebitur.getKodeKanwil(),
                crosMasterDebitur.getKodeKcu()
        );
    }

    public void checkMasterKomitmenAuthorization(LoggedUserDetailDto user, UUID idCrosMasterKomitmen) {
        val userKanwilKancab = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
        val crosMasterKomitmen = crosMasterKomitmenRepository.findById(idCrosMasterKomitmen).orElseThrow();
        checkAuthorizationByKanwilKancab(
                user.getUserType(),
                userKanwilKancab,
                crosMasterKomitmen.getKodeKanwil(),
                crosMasterKomitmen.getKodeKcu()
        );
    }

    private void checkAuthorizationByKanwilKancab(
            String userType,
            GetKanwilKancabDto userKanwilKancab,
            String dataKanwil,
            String dataKancab
    ) {
        if (!Set.of(UserTypeConstant.GCR, UserTypeConstant.DBKK).contains(userType)) {
            if (!Objects.equals(dataKanwil, userKanwilKancab.getKanwilCode())) {
                throw new CustomErrorException(Errors.UNAUTHORIZED, "User does not have permission to access this resource.");
            }
            if (UserTypeConstant.CABANG.equals(userType) && !Objects.equals(dataKancab, userKanwilKancab.getKancabCode())) {
                throw new CustomErrorException(Errors.UNAUTHORIZED, "User does not have permission to access this resource.");
            }
        }
    }
}
