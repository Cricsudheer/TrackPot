package com.example.trackpot.repository;

import com.example.trackpot.models.Frame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FrameRepository extends JpaRepository<Frame, UUID> { }