package com.example.trackpot.service;

import com.example.trackpot.models.SnookerMatch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing SnookerMatch entities.
 */
public interface MatchService {
    
    /**
     * Retrieves all matches.
     *
     * @return a list of all matches
     */
    List<SnookerMatch> getAllMatches();
    
    /**
     * Retrieves a match by its ID.
     *
     * @param id the match ID
     * @return an Optional containing the match if found, or empty if not found
     */
    Optional<SnookerMatch> getMatchById(UUID id);
    
    /**
     * Retrieves all matches for a specific player.
     *
     * @param playerId the player ID
     * @return a list of matches for the player
     */
    List<SnookerMatch> getMatchesByPlayerId(UUID playerId);
    
    /**
     * Creates a new match.
     *
     * @param match the match to create
     * @param player1Id the ID of player 1
     * @param player2Id the ID of player 2
     * @return the created match
     * @throws com.example.trackpot.exception.ResourceNotFoundException if either player is not found
     */
    SnookerMatch createMatch(SnookerMatch match, UUID player1Id, UUID player2Id);
    
    /**
     * Updates an existing match.
     *
     * @param id the ID of the match to update
     * @param match the updated match data
     * @param player1Id the ID of player 1 (if changed, otherwise null)
     * @param player2Id the ID of player 2 (if changed, otherwise null)
     * @return the updated match
     * @throws com.example.trackpot.exception.ResourceNotFoundException if the match or either player is not found
     */
    SnookerMatch updateMatch(UUID id, SnookerMatch match, UUID player1Id, UUID player2Id);
    
    /**
     * Deletes a match by its ID.
     *
     * @param id the ID of the match to delete
     * @throws com.example.trackpot.exception.ResourceNotFoundException if the match is not found
     */
    void deleteMatch(UUID id);
}