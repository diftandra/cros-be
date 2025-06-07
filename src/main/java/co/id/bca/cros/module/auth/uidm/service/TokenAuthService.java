package co.id.bca.cros.module.auth.uidm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenAuthService {
    @Value("${internal-api.url}")
    private String internalApiUrl;

    @Value("${internal-api.client-encoded-credentials}")
    private String clientEncodedCredentials;

    public Map<String, String> getToken() throws UnirestException, JsonProcessingException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post(internalApiUrl)
                .header("Authorization", "Basic " + clientEncodedCredentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", "8348978f591b3d4fb9d952fe06006118=31001e9acf356dd71eadf622fcd0bade")
                .field("grant_type", "client_credentials")
                .asString();

        return new ObjectMapper().readValue(response.getBody(), new TypeReference<>() {
        });
    }
}
