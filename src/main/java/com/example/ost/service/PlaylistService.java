package com.example.ost.service;

import com.example.ost.domain.playlist.Playlist;
import com.example.ost.domain.track.LikedTrack;
import com.example.ost.repository.LikedTrackRepository;
import com.example.ost.repository.PlaylistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    private final LikedTrackRepository likedTrackRepository;
    private final PlaylistRepository playlistRepository;

    public PlaylistService(LikedTrackRepository likedTrackRepository, PlaylistRepository playlistRepository) {
        this.likedTrackRepository = likedTrackRepository;
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public Playlist createArtistPlaylist() {
        List<LikedTrack> liked = likedTrackRepository.findAll();

        Map<String, List<LikedTrack>> groups =
                liked.stream().collect(Collectors.groupingBy(LikedTrack::getArtistName));

        Playlist playlist = new Playlist("Artist Playlist", "ARTIST", LocalDateTime.now());

        groups.values().forEach(playlist.getTracks()::addAll);

        return playlistRepository.save(playlist);
    }

    @Transactional
    public Playlist createDatePlaylist() {
        List<LikedTrack> liked = likedTrackRepository.findAll();

        Map<LocalDate, List<LikedTrack>> groups =
                liked.stream()
                        .collect(Collectors.groupingBy(t -> t.getLikedAt().toLocalDate()));

        Playlist playlist = new Playlist("Date Playlist", "DATE", LocalDateTime.now());

        groups.values().forEach(playlist.getTracks()::addAll);

        return playlistRepository.save(playlist);
    }

    @Transactional(readOnly = true)
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }
}
