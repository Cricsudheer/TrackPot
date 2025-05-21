package com.example.trackpot.mapper;

import com.example.trackpot.dto.player.PlayerRequestDto;
import com.example.trackpot.dto.player.PlayerResponseDto;
import com.example.trackpot.models.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Mapper for converting between Player entities and DTOs.
 */
@Mapper
public interface PlayerMapper {
    
    /**
     * Converts a Player entity to a PlayerResponseDto.
     *
     * @param player the Player entity
     * @return the PlayerResponseDto
     */
    PlayerResponseDto toDto(Player player);
    
    /**
     * Converts a list of Player entities to a list of PlayerResponseDtos.
     *
     * @param players the list of Player entities
     * @return the list of PlayerResponseDtos
     */
    List<PlayerResponseDto> toDtoList(List<Player> players);
    
    /**
     * Converts a PlayerRequestDto to a Player entity.
     *
     * @param dto the PlayerRequestDto
     * @return the Player entity
     */
    Player toEntity(PlayerRequestDto dto);
    
    /**
     * Updates a Player entity with data from a PlayerRequestDto.
     *
     * @param dto the PlayerRequestDto with the new data
     * @param player the Player entity to update
     */
    void updateEntityFromDto(PlayerRequestDto dto, @MappingTarget Player player);
}