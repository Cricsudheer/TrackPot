package com.example.trackpot.models;


import com.example.trackpot.enums.BallType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("POT")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class PotEvent extends ScoreEvent {
    @Enumerated(EnumType.STRING)
    @Column(name = "ball_type", nullable = false)
    private BallType ballType;

    @Column(nullable = false)
    private int points;
}
