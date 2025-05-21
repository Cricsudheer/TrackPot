package com.example.trackpot.service.impl;

import com.example.trackpot.exception.ResourceNotFoundException;
import com.example.trackpot.models.Player;
import com.example.trackpot.repository.PlayerRepository;
import com.example.trackpot.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the PlayerService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Player> getPlayerById(UUID id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(UUID id, Player playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", id));
        
        player.setName(playerDetails.getName());
        player.setAvatarUrl(playerDetails.getAvatarUrl());
        
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(UUID id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player", id));
        
        playerRepository.delete(player);
    }
}