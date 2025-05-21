package com.example.trackpot.repository;

import com.example.trackpot.models.ScoreEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScoreEventRepository extends JpaRepository<ScoreEvent , UUID> {
}
