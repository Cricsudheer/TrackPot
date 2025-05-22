package com.example.trackpot.dto.frame;

import com.example.trackpot.dto.ScoreEventDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * DTO for Frame responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrameResponseDto {
    private UUID id;
    private UUID matchId;
    private int frameIndex;
    private UUID winnerId;
    private String winnerName;
    private List<ScoreEventDto> events;
}