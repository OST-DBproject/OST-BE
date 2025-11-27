package com.example.ost.model;

import jakarta.persistence.*;

@Entity
@Table(name = "liked_tracks")
public class LikedTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    public LikedTrack() {}

    public LikedTrack(String trackId) {
        this.trackId = trackId;
    }

    public Long getId() { return id; }
    public String getTrackId() { return trackId; }
}
