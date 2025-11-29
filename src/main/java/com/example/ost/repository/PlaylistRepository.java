package com.example.ost.repository;

import com.example.ost.domain.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
