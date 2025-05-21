package com.example.trackpot.repository;

import com.example.trackpot.models.SnookerMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<SnookerMatch, UUID> { }