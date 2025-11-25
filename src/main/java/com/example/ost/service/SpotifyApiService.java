package com.example.ost.service;

import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyApiService {

    private final SpotifyAuthService auth;
    private final RestTemplate rest = new RestTemplate();

    public SpotifyApiService(SpotifyAuthService auth) {
        this.auth = auth;
    }

    public String search(String q) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + auth.getToken());

        String url = "https://api.spotify.com/v1/search?q=" + q + "&type=track&limit=10";
        return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }
}
