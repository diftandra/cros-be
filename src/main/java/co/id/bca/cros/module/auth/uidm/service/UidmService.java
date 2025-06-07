package co.id.bca.cros.module.auth.uidm.service;

import co.id.bca.cros.module.auth.uidm.dto.login.LoginOutputSchemaDto;
import co.id.bca.cros.module.auth.uidm.dto.login.LoginPayloadDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutOutputSchemaDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutPayloadDto;
import co.id.bca.cros.module.auth.uidm.dto.userfeature.UserFeatureOutputSchemaDto;
import co.id.bca.cros.shared.dto.ErrorSchema;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.exception.HttpCustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UidmService {
    private final TokenAuthService tokenAuthService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${uidm-app-access-key}")
    private String uidmAppAccessKey;

    @Value("${uidm-hostname}")
    private String uidmHostName;

    @Value("${uidm-version}")
    private String uidmVersion;

    @Value("${uidm-feature-group-code}")
    private String uidmFeatureGroupCode;

    @Value("${uidm-url-login}")
    private String uidmUrlLogin;

    @Value("${uidm-url-logout}")
    private String uidmUrlLogout;

    @Value("${uidm-url-user-features}")
    private String uidmUrlUserFeatures;

    public LoginOutputSchemaDto login(String userId, String password) {
        try {
            val loginPayload = setLoginPayload(userId, password);
            val header = setHeaders();
            val response = inquiryUidm(loginPayload, uidmUrlLogin, header, HttpMethod.POST);
            val responseString = new ObjectMapper().writeValueAsString(response);

            return new ObjectMapper().readValue(responseString, LoginOutputSchemaDto.class);
        } catch (Exception e) {
            throw new CustomErrorException(Errors.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public LogoutOutputSchemaDto logout(String userId) {
        try {
            val logoutPayload = setLogoutPayload(userId);
            val header = setHeaders();
            val response = inquiryUidm(logoutPayload, uidmUrlLogout, header, HttpMethod.POST);
            val responseString = new ObjectMapper().writeValueAsString(response);

            return new ObjectMapper().readValue(responseString, LogoutOutputSchemaDto.class);
        } catch (Exception e) {
            throw new CustomErrorException(Errors.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public UserFeatureOutputSchemaDto getUserFeatures(String userId) {
        try {
            HttpHeaders headers = setHeaders();
            headers.set("user-id", userId);
            headers.set("feature-group-code", uidmFeatureGroupCode);

            val response = inquiryUidm(null, uidmUrlUserFeatures.replace("{user-id}", userId), headers, HttpMethod.GET);
            val responseString = new ObjectMapper().writeValueAsString(response);

            return new ObjectMapper().readValue(responseString, UserFeatureOutputSchemaDto.class);
        } catch (Exception e) {
            throw new CustomErrorException(Errors.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private String setLoginPayload(String userId, String password) throws JsonProcessingException {
        LoginPayloadDto payload = new LoginPayloadDto();
        payload.setUserId(userId);
        payload.setPassword(password);
        payload.setHostName(uidmHostName);
        payload.setVersion(uidmVersion);

        return new ObjectMapper().writeValueAsString(payload);
    }

    private String setLogoutPayload(String userId) throws JsonProcessingException {
        LogoutPayloadDto payload = new LogoutPayloadDto();
        payload.setUserId(userId);

        return new ObjectMapper().writeValueAsString(payload);
    }

    private HttpHeaders setHeaders() throws UnirestException, JsonProcessingException {
        Map<String, String> mappedToken = tokenAuthService.getToken();

        HttpHeaders header = new HttpHeaders();
        header.set("app-access-key", uidmAppAccessKey);
        header.set("app_access_key", uidmAppAccessKey);
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(mappedToken.get("access_token"));
        return header;
    }

    private JsonNode inquiryUidm(String body, String url, HttpHeaders header, HttpMethod method) throws JsonProcessingException {
        try {
            log.info("url       : {}", url);
            log.info("method    : {}", method);
            log.info("header    : {}", header);
            log.info("body      : {}", body);

            ResponseEntity<JsonNode> response = restTemplate.exchange(url, method, new HttpEntity<>(body, header), JsonNode.class);

            log.info("response  : {}", response.getBody());

            return response.getBody();
        } catch (final HttpClientErrorException httpClientErrorException) {
            log.error("ERROR 4xx INQUIRY TO UIDM SERVICE");
            log.error(httpClientErrorException.getMessage());
            ErrorSchema errorSchema = new ObjectMapper().readValue(httpClientErrorException.getResponseBodyAsString(), ErrorSchema.class);

            throw new HttpCustomException(httpClientErrorException.getStatusCode(), errorSchema, errorSchema.getErrorMessage().getEnglish());
        } catch (final HttpServerErrorException httpServerErrorException) {
            log.error("ERROR 5xx INQUIRY TO UIDM SERVICE");
            log.error(httpServerErrorException.getMessage());
            ErrorSchema errorSchema = new ObjectMapper().readValue(httpServerErrorException.getResponseBodyAsString(), ErrorSchema.class);

            throw new HttpCustomException(httpServerErrorException.getStatusCode(), errorSchema, errorSchema.getErrorMessage().getEnglish());
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new CustomErrorException(Errors.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
