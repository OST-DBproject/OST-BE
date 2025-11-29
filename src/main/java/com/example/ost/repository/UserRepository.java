package com.example.ost.repository;

import com.example.ost.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySpotifyId(String spotifyId);
}
