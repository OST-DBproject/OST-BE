package com.example.ost.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    private String content;

    public Comment() {}

    public Comment(String trackId, String content) {
        this.trackId = trackId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
