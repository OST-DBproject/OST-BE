package com.example.ost.controller;

import com.example.ost.domain.playlist.Playlist;
import com.example.ost.service.PlaylistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/auto/artist")
    public Playlist autoArtist(@RequestParam Long userId) {
        return playlistService.createArtistPlaylist(userId);
    }

    @PostMapping("/auto/date")
    public Playlist autoDate(@RequestParam Long userId) {
        return playlistService.createDatePlaylist(userId);
    }

    @GetMapping
    public List<Playlist> allPlaylists() {
        return playlistService.getAllPlaylists();
    }
}
