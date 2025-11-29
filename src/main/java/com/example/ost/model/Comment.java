package com.example.ost.model;

import com.example.ost.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    public Comment() {}

    public Comment(String trackId, User user, String content) {
        this.trackId = trackId;
        this.user = user;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getTrackId() { return trackId; }
    public User getUser() { return user; }
    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }
}
