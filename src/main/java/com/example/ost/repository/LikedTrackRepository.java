package com.example.ost.repository;

import com.example.ost.model.LikedTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedTrackRepository extends JpaRepository<LikedTrack, Long> {
}
