package com.example.ost.service;

import com.example.ost.domain.user.User;
import com.example.ost.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    public User getUser(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public User registerOrLogin(String spotifyId, String name, String image) {
        return repo.findBySpotifyId(spotifyId)
                .orElseGet(() -> repo.save(new User(spotifyId, name, image)));
    }
}
