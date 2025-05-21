package com.example.trackpot.mapper;

import com.example.trackpot.dto.match.MatchRequestDto;
import com.example.trackpot.dto.match.MatchResponseDto;
import com.example.trackpot.models.Frame;
import com.example.trackpot.models.Player;
import com.example.trackpot.models.SnookerMatch;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper for converting between SnookerMatch entities and DTOs.
 */
@Mapper(uses = {PlayerMapper.class})
public interface MatchMapper {
    
    /**
     * Converts a SnookerMatch entity to a MatchResponseDto.
     * Calculates player scores and extracts frame IDs.
     *
     * @param match the SnookerMatch entity
     * @return the MatchResponseDto
     */
    @Mapping(target = "player1Score", expression = "java(calculatePlayer1Score(match))")
    @Mapping(target = "player2Score", expression = "java(calculatePlayer2Score(match))")
    @Mapping(target = "frameIds", expression = "java(extractFrameIds(match))")
    MatchResponseDto toDto(SnookerMatch match);
    
    /**
     * Converts a list of SnookerMatch entities to a list of MatchResponseDtos.
     *
     * @param matches the list of SnookerMatch entities
     * @return the list of MatchResponseDtos
     */
    List<MatchResponseDto> toDtoList(List<SnookerMatch> matches);
    
    /**
     * Creates a new SnookerMatch entity from a MatchRequestDto.
     * Player1 and Player2 are set separately.
     *
     * @param dto the MatchRequestDto
     * @return the SnookerMatch entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player1", ignore = true)
    @Mapping(target = "player2", ignore = true)
    @Mapping(target = "frames", ignore = true)
    SnookerMatch toEntity(MatchRequestDto dto);
    
    /**
     * Updates a SnookerMatch entity with data from a MatchRequestDto.
     * Player1 and Player2 are updated separately.
     *
     * @param dto the MatchRequestDto with the new data
     * @param match the SnookerMatch entity to update
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player1", ignore = true)
    @Mapping(target = "player2", ignore = true)
    @Mapping(target = "frames", ignore = true)
    void updateEntityFromDto(MatchRequestDto dto, @MappingTarget SnookerMatch match);
    
    /**
     * Calculates the score for player 1 (number of frames won).
     *
     * @param match the SnookerMatch entity
     * @return the score for player 1
     */
    default int calculatePlayer1Score(SnookerMatch match) {
        if (match.getFrames() == null) return 0;
        return (int) match.getFrames().stream()
                .filter(frame -> frame.getWinner() != null && 
                        frame.getWinner().getId().equals(match.getPlayer1().getId()))
                .count();
    }
    
    /**
     * Calculates the score for player 2 (number of frames won).
     *
     * @param match the SnookerMatch entity
     * @return the score for player 2
     */
    default int calculatePlayer2Score(SnookerMatch match) {
        if (match.getFrames() == null) return 0;
        return (int) match.getFrames().stream()
                .filter(frame -> frame.getWinner() != null && 
                        frame.getWinner().getId().equals(match.getPlayer2().getId()))
                .count();
    }
    
    /**
     * Extracts the IDs of all frames in the match.
     *
     * @param match the SnookerMatch entity
     * @return a list of frame IDs
     */
    default List<UUID> extractFrameIds(SnookerMatch match) {
        if (match.getFrames() == null) return List.of();
        return match.getFrames().stream()
                .map(Frame::getId)
                .collect(Collectors.toList());
    }
}