package com.example.ost.controller;

import com.example.ost.service.SpotifySearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class TrackController {

    private final SpotifySearchService searchService;

    public TrackController(SpotifySearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    @ResponseBody
    public String searchTracks(@RequestParam String query) {
        return searchService.searchTrack(query);
    }
}
