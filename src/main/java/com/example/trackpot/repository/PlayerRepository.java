package com.example.trackpot.repository;

import com.example.trackpot.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> { }