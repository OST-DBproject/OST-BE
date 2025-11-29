package com.example.ost.service;

import com.example.ost.domain.track.LikedTrack;
import com.example.ost.domain.user.User;
import com.example.ost.dto.SpotifyTrackInfoDto;
import com.example.ost.repository.LikedTrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrackManagementService {

    private final LikedTrackRepository likedRepo;
    private final UserService userService;
    private final SpotifyApiClient spotify;

    public TrackManagementService(LikedTrackRepository likedRepo, UserService userService, SpotifyApiClient spotify) {
        this.likedRepo = likedRepo;
        this.userService = userService;
        this.spotify = spotify;
    }

    @Transactional
    public void saveTrack(Long userId, String trackId) {
        User user = userService.getUser(userId);

        if (likedRepo.existsByUserAndSpotifyTrackId(user, trackId)) return;

        SpotifyTrackInfoDto info = spotify.getTrackInfo(trackId);

        LikedTrack track = new LikedTrack(
                user,
                info.getId(),
                info.getName(),
                info.getArtistName(),
                info.getAlbumName()
        );

        likedRepo.save(track);
    }

    @Transactional
    public void removeTrack(Long userId, String trackId) {
        User user = userService.getUser(userId);
        likedRepo.deleteByUserAndSpotifyTrackId(user, trackId);
    }

    @Transactional(readOnly = true)
    public List<LikedTrack> getUserTracks(Long userId) {
        User user = userService.getUser(userId);
        return likedRepo.findAllByUser(user);
    }

    @Transactional(readOnly = true)
    public Map<String, List<LikedTrack>> groupByArtist(Long userId) {
        return getUserTracks(userId).stream()
                .collect(Collectors.groupingBy(LikedTrack::getArtistName));
    }

    @Transactional(readOnly = true)
    public Map<LocalDate, List<LikedTrack>> groupByDate(Long userId) {
        return getUserTracks(userId).stream()
                .collect(Collectors.groupingBy(t -> t.getLikedAt().toLocalDate()));
    }
}
