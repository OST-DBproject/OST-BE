package com.example.ost.controller;

import com.example.ost.service.TrackManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackManagementController {

    private final TrackManagementService service;

    public TrackManagementController(TrackManagementService service) {
        this.service = service;
    }

    // 좋아요
    @PostMapping("/track/like")
    public String like(@RequestParam String id) {
        service.saveTrack(id);
        return "saved";
    }

    // 좋아요 취소
    @DeleteMapping("/track/unlike")
    public String unlike(@RequestParam String id) {
        service.removeTrack(id);
        return "removed";
    }

    // 좋아요 목록 조회
    @GetMapping("/track/liked")
    public String likedTracks() {
        return service.getLikedTracks();
    }
}
