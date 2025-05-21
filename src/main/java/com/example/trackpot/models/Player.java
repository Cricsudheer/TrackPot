package com.example.trackpot.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "player")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Player {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany(mappedBy = "player")
    private List<ScoreEvent> events = new ArrayList<>();
}
