package com.example.ost.service;

import com.example.ost.domain.user.User;
import com.example.ost.model.Comment;
import com.example.ost.repository.CommentRepository;
import com.example.ost.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    public CommentService(CommentRepository commentRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    public Comment addComment(String spotifyId, String trackId, String content) {
        User user = userRepo.findBySpotifyId(spotifyId)
                .orElseThrow(); // 유저 없으면 오류

        Comment comment = new Comment(trackId, user, content);
        return commentRepo.save(comment);
    }

    public List<Comment> getComments(String trackId) {
        return commentRepo.findByTrackId(trackId);
    }

    public Comment updateComment(Long id, String newContent) {
        Comment c = commentRepo.findById(id).orElseThrow();
        c.setContent(newContent);
        return commentRepo.save(c);
    }

    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}
