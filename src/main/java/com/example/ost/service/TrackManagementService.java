package com.example.ost.service;

import com.example.ost.domain.track.LikedTrack;
import com.example.ost.dto.SpotifyTrackInfoDto;
import com.example.ost.repository.LikedTrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrackManagementService {

    private final LikedTrackRepository likedTrackRepository;
    private final SpotifyApiClient spotifyApiClient; // ★ 추가됨

    public TrackManagementService(LikedTrackRepository likedTrackRepository,
                                  SpotifyApiClient spotifyApiClient) {  // ★ 추가됨
        this.likedTrackRepository = likedTrackRepository;
        this.spotifyApiClient = spotifyApiClient;     // ★ 추가됨
    }

    @Transactional
    public void saveTrack(String spotifyTrackId) {

        // 이미 존재하면 저장 안 함
        if (likedTrackRepository.existsBySpotifyTrackId(spotifyTrackId)) {
            return;
        }

        // ★ Spotify API 호출해서 실제 정보 가져오기
        SpotifyTrackInfoDto info = spotifyApiClient.getTrackInfo(spotifyTrackId);

        LikedTrack track = new LikedTrack(
                info.getId(),          // id
                info.getName(),        // track name
                info.getArtistName(),  // artist
                info.getAlbumName(),   // album
                LocalDateTime.now()
        );


        likedTrackRepository.save(track);
    }

    @Transactional
    public void removeTrack(String spotifyTrackId) {
        likedTrackRepository.deleteBySpotifyTrackId(spotifyTrackId);
    }

    @Transactional(readOnly = true)
    public List<LikedTrack> getLikedTracks() {
        return likedTrackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Map<String, List<LikedTrack>> groupByArtist() {
        List<LikedTrack> tracks = likedTrackRepository.findAll();
        return tracks.stream()
                .collect(Collectors.groupingBy(LikedTrack::getArtistName));
    }

    @Transactional(readOnly = true)
    public Map<LocalDate, List<LikedTrack>> groupByDate() {
        List<LikedTrack> tracks = likedTrackRepository.findAll();
        return tracks.stream()
                .collect(Collectors.groupingBy(t -> t.getLikedAt().toLocalDate()));
    }
}
