package com.example.ost.controller;

import com.example.ost.domain.track.LikedTrack;
import com.example.ost.service.TrackManagementService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/track")
public class TrackManagementController {

    private final TrackManagementService service;

    public TrackManagementController(TrackManagementService service) {
        this.service = service;
    }

    @PostMapping("/like")
    public String like(@RequestParam Long userId, @RequestParam String trackId) {
        service.saveTrack(userId, trackId);
        return "saved";
    }

    @DeleteMapping("/unlike")
    public String unlike(@RequestParam Long userId, @RequestParam String trackId) {
        service.removeTrack(userId, trackId);
        return "removed";
    }

    @GetMapping("/liked")
    public List<LikedTrack> liked(@RequestParam Long userId) {
        return service.getUserTracks(userId);
    }

    @GetMapping("/group/artist")
    public Map<String, List<LikedTrack>> groupArtist(@RequestParam Long userId) {
        return service.groupByArtist(userId);
    }

    @GetMapping("/group/date")
    public Map<LocalDate, List<LikedTrack>> groupDate(@RequestParam Long userId) {
        return service.groupByDate(userId);
    }
}
