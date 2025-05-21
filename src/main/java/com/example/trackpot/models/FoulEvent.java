package com.example.trackpot.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("FOUL")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class FoulEvent extends ScoreEvent {
    @Column(name = "foul_points", nullable = false)
    private int foulPoints;

    @Column(columnDefinition = "TEXT")
    private String reason;
}
