package com.example.trackpot.dto.event;

import com.example.trackpot.enums.BallType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for PotEvent entities.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PotEventDto extends ScoreEventBaseDto {
    private BallType ballType;
    private int points;
}