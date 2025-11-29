package com.example.ost.domain.playlist;

import com.example.ost.domain.track.LikedTrack;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;  // ARTIST / DATE / SIMILAR

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name="playlist_tracks",
            joinColumns=@JoinColumn(name="playlist_id"),
            inverseJoinColumns=@JoinColumn(name="track_id")
    )
    private List<LikedTrack> tracks = new ArrayList<>();

    protected Playlist() {}

    public Playlist(String name, String type, LocalDateTime createdAt) {
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public List<LikedTrack> getTracks() { return tracks; }
}
