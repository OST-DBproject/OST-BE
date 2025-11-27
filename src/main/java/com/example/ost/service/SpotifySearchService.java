package com.example.ost.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifySearchService {

    private final SpotifyAuthService authService;
    private final RestTemplate rest = new RestTemplate();

    public SpotifySearchService(SpotifyAuthService authService) {
        this.authService = authService;
    }

    public String searchTrack(String query) {
        String token = authService.getToken();

        String url = "https://api.spotify.com/v1/search?q="
                + query + "&type=track&limit=10";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
