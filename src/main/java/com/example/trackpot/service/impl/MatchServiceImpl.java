package com.example.trackpot.service.impl;

import com.example.trackpot.enums.MatchStatus;
import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.models.Player;
import com.example.trackpot.models.SnookerMatch;
import com.example.trackpot.repository.MatchRepository;
import com.example.trackpot.repository.PlayerRepository;
import com.example.trackpot.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the MatchService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SnookerMatch> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SnookerMatch> getMatchById(UUID id) {
        return matchRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SnookerMatch> getMatchesByPlayerId(UUID playerId) {
        // Ensure player exists
        playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player", playerId));
        
        // Get all matches and filter by player
        return matchRepository.findAll().stream()
                .filter(match -> match.getPlayer1().getId().equals(playerId) || 
                                match.getPlayer2().getId().equals(playerId))
                .collect(Collectors.toList());
    }

    @Override
    public SnookerMatch createMatch(SnookerMatch match, UUID player1Id, UUID player2Id) {
        // Validate players
        Player player1 = playerRepository.findById(player1Id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", player1Id));
        Player player2 = playerRepository.findById(player2Id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", player2Id));
        
        // Set players
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        
        // Set default values if not provided
        if (match.getStartTime() == null) {
            match.setStartTime(Instant.now());
        }
        
        if (match.getStatus() == null) {
            match.setStatus(MatchStatus.NOT_STARTED);
        }
        
        return matchRepository.save(match);
    }

    @Override
    public SnookerMatch updateMatch(UUID id, SnookerMatch matchDetails, UUID player1Id, UUID player2Id) {
        SnookerMatch match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", id));
        
        // Update players if IDs are provided
        if (player1Id != null) {
            Player player1 = playerRepository.findById(player1Id)
                    .orElseThrow(() -> new ResourceNotFoundException("Player", player1Id));
            match.setPlayer1(player1);
        }
        
        if (player2Id != null) {
            Player player2 = playerRepository.findById(player2Id)
                    .orElseThrow(() -> new ResourceNotFoundException("Player", player2Id));
            match.setPlayer2(player2);
        }
        
        // Update match details
        match.setBestOfFrames(matchDetails.getBestOfFrames());
        match.setStartTime(matchDetails.getStartTime());
        match.setEndTime(matchDetails.getEndTime());
        match.setStatus(matchDetails.getStatus());
        
        return matchRepository.save(match);
    }

    @Override
    public void deleteMatch(UUID id) {
        SnookerMatch match = matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match", id));
        
        matchRepository.delete(match);
    }
}