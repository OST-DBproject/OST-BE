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
    public Playlist autoArtist() {
        return playlistService.createArtistPlaylist();
    }

    @PostMapping("/auto/date")
    public Playlist autoDate() {
        return playlistService.createDatePlaylist();
    }

    @GetMapping
    public List<Playlist> allPlaylists() {
        return playlistService.getAllPlaylists();
    }
}
