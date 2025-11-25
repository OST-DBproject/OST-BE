package com.example.ost.service;

import com.example.ost.SpotifyConfig;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@Service
public class SpotifyAuthService {

    private final SpotifyConfig config;
    private final RestTemplate rest = new RestTemplate();
    private String accessToken;

    public SpotifyAuthService(SpotifyConfig config) {
        this.config = config;
    }

    public String loginUrl() {
        List<String> scopes = List.of(
                "user-library-read",
                "user-library-modify"
        );

        URI uri = UriComponentsBuilder
                .fromUriString("https://accounts.spotify.com/authorize")
                .queryParam("client_id", config.getClientId())
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", config.getRedirectUri())
                .queryParam("scope", String.join("%20", scopes))
                .build(true)
                .encode()
                .toUri();

        return uri.toString();
    }


    public void requestToken(String code) {
        String url = "https://accounts.spotify.com/api/token";

        String body = "grant_type=authorization_code"
                + "&code=" + code
                + "&redirect_uri=" + config.getRedirectUri();

        String auth = config.getClientId() + ":" + config.getClientSecret();
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encoded);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        TokenResponse res = rest.exchange(url, HttpMethod.POST, entity, TokenResponse.class).getBody();
        if (res != null) accessToken = res.access_token;
    }

    public String getToken() {
        return accessToken;
    }

    static class TokenResponse {
        public String access_token;
    }
}
