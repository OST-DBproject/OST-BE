package com.example.ost.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackManagementService {

    private final SpotifyAuthService authService;
    private final RestTemplate rest = new RestTemplate();

    public TrackManagementService(SpotifyAuthService authService) {
        this.authService = authService;
    }

    // 좋아요 (Saved Track)
    public void saveTrack(String trackId) {
        String url = "https://api.spotify.com/v1/me/tracks?ids=" + trackId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getToken());

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        rest.exchange(url, HttpMethod.PUT, entity, Void.class);
    }

    // 좋아요 취소
    public void removeTrack(String trackId) {
        String url = "https://api.spotify.com/v1/me/tracks?ids=" + trackId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getToken());

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        rest.exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    // 좋아요 목록 조회
    public String getLikedTracks() {
        String url = "https://api.spotify.com/v1/me/tracks?limit=20";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getToken());

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
