package com.example.trackpot.service;

import com.example.trackpot.models.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing Player entities.
 */
public interface PlayerService {
    
    /**
     * Retrieves all players.
     *
     * @return a list of all players
     */
    List<Player> getAllPlayers();
    
    /**
     * Retrieves a player by their ID.
     *
     * @param id the player ID
     * @return an Optional containing the player if found, or empty if not found
     */
    Optional<Player> getPlayerById(UUID id);
    
    /**
     * Creates a new player.
     *
     * @param player the player to create
     * @return the created player
     */
    Player createPlayer(Player player);
    
    /**
     * Updates an existing player.
     *
     * @param id the ID of the player to update
     * @param player the updated player data
     * @return the updated player
     * @throws com.example.trackpot.exception.ResourceNotFoundException if the player is not found
     */
    Player updatePlayer(UUID id, Player player);
    
    /**
     * Deletes a player by their ID.
     *
     * @param id the ID of the player to delete
     * @throws com.example.trackpot.exception.ResourceNotFoundException if the player is not found
     */
    void deletePlayer(UUID id);
}