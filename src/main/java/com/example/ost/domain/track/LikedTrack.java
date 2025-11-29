package com.example.ost.domain.track;

import com.example.ost.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "liked_track")
public class LikedTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String spotifyTrackId;

    @Column(nullable = false)
    private String trackName;

    @Column(nullable = false)
    private String artistName;

    private String albumName;

    @Column(nullable = false)
    private LocalDateTime likedAt;

    protected LikedTrack() {}

    public LikedTrack(User user,
                      String spotifyTrackId,
                      String trackName,
                      String artistName,
                      String albumName) {

        this.user = user;
        this.spotifyTrackId = spotifyTrackId;
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.likedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getSpotifyTrackId() {
        return spotifyTrackId;
    }

    public void setSpotifyTrackId(String spotifyTrackId) {
        this.spotifyTrackId = spotifyTrackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }
}
